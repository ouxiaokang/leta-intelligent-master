package cn.leta.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by xiegengcai on 2018/7/5.
 *
 * @author Xie Gengcai
 */
@NoArgsConstructor
@Data
@ApiModel(value = "Sort", description = "排序")
public class Sort implements Iterable<Sort.Order>, Serializable {
    public static final Sort.Direction DEFAULT_DIRECTION = Direction.ASC;
    @ApiModelProperty("排序字段列表")
    private List<Order> orders;

    public Sort(Sort.Order... orders) {
        this(Arrays.asList(orders));
    }

    public Sort(List<Sort.Order> orders) {
        if (null != orders && !orders.isEmpty()) {
            this.orders = orders;
        } else {
            throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
        }
    }

    public Sort(String... properties) {
        this(DEFAULT_DIRECTION, properties);
    }

    public Sort(Sort.Direction direction, String... properties) {
        this(direction, (List)(properties == null ? new ArrayList() : Arrays.asList(properties)));
    }

    public Sort(Sort.Direction direction, List<String> properties) {
        if (properties != null && !properties.isEmpty()) {
            this.orders = new ArrayList(properties.size());
            Iterator var3 = properties.iterator();

            while(var3.hasNext()) {
                String property = (String)var3.next();
                this.orders.add(new Sort.Order(direction, property));
            }

        } else {
            throw new IllegalArgumentException("You have to provide at least one property to sort by!");
        }
    }

    @Override
    public void forEach(Consumer<? super Order> action) {
        Objects.requireNonNull(action);
        if (orders != null) {
            orders.parallelStream().forEach(order -> action.accept(order));
        }
    }

    /**
     * 合併排序條件
     * @param sort
     * @return
     */
    public Sort and(Sort sort) {
        if (sort == null & CollectionUtils.isEmpty(sort.orders)) {
            return this;
        }
        if (this.orders == null) {
            this.orders = sort.orders;
            return this;
        }
        sort.orders.forEach(order -> this.orders.add(order));
        return this;
    }

    public void addOrder(Order order) {}

    @ApiModel(value = "Sort.Order", description = "排序模型")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Order implements Serializable{
        @ApiModelProperty(value = "排序方式", allowableValues = "ASC, DESC", example = "ASC")
        private Sort.Direction direction;
        @ApiModelProperty("排序字段")
        private String property;
    }

    @Override
    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Sort)) {
            return false;
        } else {
            Sort that = (Sort)obj;
            return this.orders.equals(that.orders);
        }
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.orders.hashCode();
        return result;
    }

    public String toString() {
        return StringUtils.collectionToCommaDelimitedString(this.orders);
    }
    public enum  Direction{
        ASC,
        DESC;

        private Direction() {
        }

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Sort.Direction fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception var2) {
                throw new IllegalArgumentException(String.format("Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), var2);
            }
        }

        public static Sort.Direction fromStringOrNull(String value) {
            try {
                return fromString(value);
            } catch (IllegalArgumentException var2) {
                return null;
            }
        }
    }
}
