package cn.leta.common.bean;

import cn.leta.common.utils.BeanUtil;
import cn.leta.common.vo.LetaPage;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义属性拷贝实现抽象类。适应source和target属性类型或名称不一致的情况
 * <br>
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/24.
 * <h3>应用举例</h3>
 * <pre class="code">
 * public class DTO {
 *     private String name;
 *     private String phone;
 *     private List<String> attachments;
 *     // 省略getter setter
 * }
 *
 * public class Entity {
 *     private String name;
 *     private String cellPhone;
 *     private String attachments;
 *     // 省略getter setter
 * }
 *
 * NotMatchTypeReferenceCopier<DTO, Entity> dto2EntityCopier =  (source, target) -> {
 *      // 类型不一致
 *     if(source.getAttachments() != null && source.getAttachments().size() > 0) {
 *         target.setAttachments(JSON.toJSONString(source.getAttachments()));
 *     }
 *     // 名称不一致
 *     if(StringUtils.hasText(source.getPhone())){
 *         target.setCellPhone(source.getPhone());
 *     }
 *     return target;
 * };
 *
 * // 单个对象属性拷贝
 * dto2EntityCopier.copyProperties(dto, entity);
 *
 * // 列表对象属性拷贝
 * List<DTO> dtos = ... ;
 * List<Entity> entityList = dto2EntityCopier.copyProperties(dtos, Entity.class);
 *
 * NotMatchTypeReferenceCopier<Entity, DTO> entity2DTOCopier = (source, target) -> {
 *     if(StringUtrils.hasText(source.getCellPhone())) {
 *         target.setPhone(source.getCellPhone());
 *     }
 *     if(StringUtrils.hasText(source.getAttachments())) {
 *         target.setAttachments(JSON.parseObject(source.getAttachments(), DTO.class));
 *     }
 *     return target;
 * };
 *
 * //toLetaPage
 * Page<Entity> page = service.selectPage();
 * LetaPage letaPage = entity2DTOCopier.toLetaPage(page, DTO.class);
 * </pre>
 * @author Xie Gengcai
 */
public interface NotMatchTypeReferenceCopier <S, T> {

    /**
     * 属性拷贝
     * @param source 源实例
     * @param target 目标实例
     * @return
     */
    default T copyProperties(S source, T target) {
        target = BeanUtil.copyProperties(source, target);
        return notMatchTypeReferenceCopy(source, target);
    }
    /**
     * 类型不匹配的属性拷贝
     * @param source 源实例
     * @param target 目标实例
     * @return
     */
    T notMatchTypeReferenceCopy(S source, T target);

    /**
     * 列表对象属性拷贝
     * @param sources 源列表实例
     * @param targetClass 目标列表实例项类型
     * @return
     */
    default List<T> copyProperties(List<S> sources, Class<T> targetClass) {
        Objects.requireNonNull(sources);
        List<T> targets = new ArrayList<>(sources.size());
        sources.parallelStream().forEach(s -> targets.add(this.copyProperties(s, BeanUtils.instantiateClass(targetClass))));
        return targets;
    }

    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}，records用{@link #copyProperties(S, T)}拷贝
     * @param page 源page对象{@link Page}转
     * @param targetClass 目标page对象{@link LetaPage}records项类型
     * @return
     */
    default LetaPage<T> toLetaPage(Page<S> page, Class<T> targetClass) {
        LetaPage<T> letaPage = BeanUtil.toSimpleLetaPage(page);
        letaPage.setRecords(this.copyProperties(page.getRecords(), targetClass));
        return letaPage;
    }
}
