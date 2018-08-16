package cn.leta.common.utils;

import cn.leta.common.vo.LetaPage;
import cn.leta.common.vo.Sort;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xiegengcai on 2018/7/3.
 *
 * @author Xie Gengcai
 */
public final class BeanUtil {
    private BeanUtil(){}

    /**
     * 数据转换。
     * @param source 源对象
     * @param target 目标对象
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     * @return
     */
    public static <S, T> T copyProperties(S source, T target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 列表数据转换。
     * @param sources 源数据列表
     * @param clazz 目标列表项class
     * @param <S> 源数据项类型
     * @param <T> 目标数据项类型
     * @return
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Class<T> clazz) {
        Objects.requireNonNull(sources);
        List<T> targets = new ArrayList<>(sources.size());
        sources.parallelStream().forEach(s -> targets.add(copyProperties(s, BeanUtils.instantiateClass(clazz))));
        return targets;
    }

    /**
     * 将map转为bean
     * @param map
     * @param beanClass
     * @param <T> 目标bean类型
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        T obj = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }
        return obj;
    }

    /**
     * 将Java Bean转为Map
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> objectToMap (T obj) throws Exception{
        if (obj == null) {
            return null;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<String, Object> map = new HashMap<>(propertyDescriptors.length, 1.0f);
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            if (value != null) { // 或略空值
                map.put(key, value);
            }
        }

        return map;
    }
    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}。适用于两者Records项类型一致的情况
     * @param page 源page
     * @param <T> Records项类型
     * @return
     */
    public static <T> LetaPage<T> toLetaPage(Page<T> page) {
        LetaPage<T> letaPage = toSimpleLetaPage(page);
        letaPage.setRecords(BeanUtil.copyProperties(page.getRecords(), new ArrayList<>()));
        return letaPage;
    }

    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}。适用于两者Records项类型一致的情况
     * @param page 源page
     * @param ignoreCondition 转换时是否忽略条件
     * @param <T>
     * @return
     */
    public static <T> LetaPage<T> toLetaPage(Page<T> page, boolean ignoreCondition) {
        LetaPage<T> letaPage = toSimpleLetaPage(page, ignoreCondition);
        letaPage.setRecords(page.getRecords());
        return letaPage;
    }

    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}。适用于两者Records项类型不一致的情况
     * @param page 源page
     * @param clazz 目标{@link LetaPage}Records项类型class
     * @param <T> 源{@link Page}Records项类型
     * @param <R> 目标{@link LetaPage}Records项类型
     * @return
     */
    public static <T, R> LetaPage<R> toLetaPage(Page<T> page, Class<R> clazz) {
        LetaPage<R> letaPage = toSimpleLetaPage(page);
        letaPage.setRecords(BeanUtil.copyProperties(page.getRecords(),  clazz));
        return letaPage;
    }

    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}。适用于两者Records项类型不一致的情况
     * @param page 源page
     * @param clazz 目标{@link LetaPage}Records项类型class
     * @param ignoreCondition 转换时是否忽略条件
     * @param <T> 源{@link Page}Records项类型
     * @param <R> 目标{@link LetaPage}Records项类型
     * @return
     */
    public static <T, R> LetaPage<R> toLetaPage(Page<T> page, Class<R> clazz, boolean ignoreCondition) {
        LetaPage<R> letaPage = toSimpleLetaPage(page, ignoreCondition);
        letaPage.setRecords(BeanUtil.copyProperties(page.getRecords(),  clazz));
        return letaPage;
    }

    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}，但不处理records
     * @param page
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> LetaPage<T> toSimpleLetaPage(Page<R> page) {
        return toSimpleLetaPage(page, false);
    }
    /**
     * 将Mybatisplus的{@link Page}转换成{@link LetaPage}，但不处理records
     * @param page
     * @param ignoreCondition 是否忽略查询条件
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T>  LetaPage<T> toSimpleLetaPage(Page<R> page, boolean ignoreCondition) {
        Objects.requireNonNull(page);
        Sort sort = new Sort();
        List<String> ascs = page.getAscs();
        if (ascs != null && ascs.size() > 0) {
            sort.and(new Sort(Sort.Direction.ASC, ascs));
        }
        List<String> descs = page.getDescs();
        if (descs != null && descs.size() > 0) {
            sort.and(new Sort(Sort.Direction.DESC, descs));
        }
        long totalPage = 0;
        if (page.getTotal() > 0 && page.getSize() > 0) {
            totalPage = page.getTotal() / page.getSize();
            if (page.getTotal() % page.getSize() != 0) {
                totalPage += 1;
            }
        }
        if (ignoreCondition) {
            page.setCondition(null);
        }else if (page.getCondition() != null && !page.getCondition().isEmpty()) {
            // 将查询条件转换为驼峰命名方式
            Map<String, Object> condition = page.getCondition();
            // loadFactor 设置为1避免扩容
            Map<String, Object> camelCaseCondition = new HashMap<>(condition.size(), 1.0f);
            page.getCondition().entrySet().forEach(entry ->camelCaseCondition.put(StringUtils.underlineToCamel(entry.getKey()), entry.getValue()));
            page.setCondition(camelCaseCondition);
        }

        return new LetaPage<>(page.getCurrent(), page.getSize(), totalPage, page.getTotal(), null, page.getCondition(), sort);
    }

}
