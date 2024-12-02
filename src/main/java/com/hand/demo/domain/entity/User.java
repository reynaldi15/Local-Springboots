package com.hand.demo.domain.entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import org.hzero.core.util.Regexs;
import org.hzero.export.annotation.ExcelColumn;

@ApiModel("用户信息") // Swagger 实体描述
@ModifyAudit //在类上使用，启用审计字段支持，实体类加上该注解后，插入和更新会启动对 lastUpdateDate、lastUpdatedBy 自维护字段支持
@VersionAudit //在类上使用，启用objectVersionNumber自维护支持，插入一条数据objectVersionNumber默认为1，每次update后objectVersionNumber自增1
@Table(name = "todo_user") // 表映射
@JsonInclude(JsonInclude.Include.NON_NULL) // 数据返回前端时，排除为空的字段
public class User extends AuditDomain { //AuditDomain包含5个自维护字段，使用@ModifyAudit和@VersionAudit的实体类要继承该类
//    @Id // 主键主键，注意是 javax.persistence.Id
//    @GeneratedValue //对于自增张、序列（SEQUENCE）类型的主键，需要添加该注解
//    private Long id;
//    @Length(max = 30) // 长度控制
//    @NotBlank // 非空控制
//    @ApiModelProperty("Employee Name") // Swagger 字段描述
//    private String employeeName;
//    @Length(max = 30)
//    @NotBlank
//    @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") // 格式控制
//    @ApiModelProperty("Employee Number")
//    private String employeeNumber;
//    @Length(max = 60)
//    @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect")
//    @ApiModelProperty("Email")
//    private String email;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public @Length(max = 30) @NotBlank String getEmployeeName() {
//        return employeeName;
//    }
//
//    public void setEmployeeName(@Length(max = 30) @NotBlank String employeeName) {
//        this.employeeName = employeeName;
//    }
//
//    public @Length(max = 30) @NotBlank @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") String getEmployeeNumber() {
//        return employeeNumber;
//    }
//
//    public void setEmployeeNumber(@Length(max = 30) @NotBlank @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") String employeeNumber) {
//        this.employeeNumber = employeeNumber;
//    }
//
//    public @Length(max = 60) @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@Length(max = 60) @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect") String email) {
//        this.email = email;
//    }
    public static final String FIELD_ID="id";
    public static final String FIELD_EMAIL="email";
    public static final String FIELD_EMPLOYEE_NAME="employeeName";
    public static final String FIELD_EMPLOYEE_NUMBER="employeeNumber";
    public static final String FIELD_USER_ACCOUNT="userAccount";
    public static final String FIELD_PASSWORD="userPassword";

    @ApiModelProperty("Table Id")
    @Id
    @GeneratedValue
    private  Long id;

    @ApiModelProperty(value = "Email")
    @ExcelColumn(zh = "email",en = "email", order= 1)
    private String email;

    @ApiModelProperty(value ="Employee Name") // Swagger 字段描述
    @ExcelColumn(zh = "Employee Name",en = "Employee Name",order= 2)
    private String employeeName;

    @ApiModelProperty(value ="Employee Number") // Swagger 字段描述
    @ExcelColumn(zh = "Employee Number",en = "Employee Number",order= 3)
    private String employeeNumber;

    @ApiModelProperty(value ="User Account") // Swagger 字段描述
    @ExcelColumn(zh = "User Account",en = "User Account",order= 4)
    private String userAccount;

    @ApiModelProperty(value ="User Password") // Swagger 字段描述
    @ExcelColumn(zh = "User Password",en = "User Password",order= 5)
    private String userPassword;

    public Long getId() {
        return null;
    }
}