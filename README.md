## Lesson 6: Spring Data JPA

**1. TIME**

- `@Temporal(TemporalType.TIMESTAMP)`: chỉ định kiểu dữ liệu bao gồm ngày và giờ.
- `@Temporal(TemporalType.DATE)`: chỉ định kiểu dữ liệu chỉ bao gồm ngày.
- `@CreationTimestamp`, `@UpdateTimestamp`: ngày + giờ.
- `LocalDate`(Java 8+): thông tin ngày.
- `LocalDateTime`: thông tin ngày + giờ.

```java

@Column(name = "created_at")
@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
private LocalDateTime createdAt;
```

**2. ENUM**

- `@JsonProperty("male")`: được sử dụng để chuyển đổi các đối tượng Java sang và từ JSON (JavaScript Object Notation).
  `Ví dụ`, nếu hằng số MALE được sử dụng trong một đối tượng Java, nó sẽ được tuần tự hóa thành "male" trong JSON.

```java
public enum UserStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("inactive")
    INACTIVE,
    @JsonProperty("none")
    NONE;
}
```

- `@JdbcTypeCode(SqlTypes.NAMED_ENUM)`: chỉ định rằng cột gender trong cơ sở dữ liệu sẽ sử dụng kiểu ENUM.
- `@Enumerated(EnumType.STRING)`: được sử dụng để ánh xạ các giá trị enum dưới dạng chuỗi trong Java với các giá trị
  enum có tên tương ứng trong cơ sở dữ liệu.

```java

@Enumerated(EnumType.STRING)
@JdbcTypeCode(SqlTypes.NAMED_ENUM)
private Gender gender;
  ```
