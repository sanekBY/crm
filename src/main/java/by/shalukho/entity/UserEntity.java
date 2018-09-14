package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"LOGIN"})})
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = UserDto.class)
public class UserEntity extends AbstractEntity {

    @NotNull
    @Column(name = "LOGIN", nullable = false)
    private String login;

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private RoleEnum role;
}
