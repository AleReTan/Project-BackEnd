package ru.vsu.entity;

import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;

import java.util.Objects;

@ObjectTypeId(id = 10)
public class CustomerEntity extends ObjectEntity {
    @ParamAttributeId(id = 27)
    private String customerLogin;
    @ParamAttributeId(id = 28)
    private String customerURL;
    @ParamAttributeId(id = 30)
    private String customerAccessLogin;
    @ParamAttributeId(id = 31)
    private String customerAccessPassword;

    public CustomerEntity() {
    }

    public CustomerEntity(long id, String name, long typeId, String customerLogin, String customerURL, String customerAccessLogin, String customerAccessPassword) {
        super(id, name, typeId);
        this.customerLogin = customerLogin;
        this.customerURL = customerURL;
        this.customerAccessLogin = customerAccessLogin;
        this.customerAccessPassword = customerAccessPassword;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }

    public String getCustomerURL() {
        return customerURL;
    }

    public void setCustomerURL(String customerURL) {
        this.customerURL = customerURL;
    }

    public String getCustomerAccessLogin() {
        return customerAccessLogin;
    }

    public void setCustomerAccessLogin(String customerAccessLogin) {
        this.customerAccessLogin = customerAccessLogin;
    }

    public String getCustomerAccessPassword() {
        return customerAccessPassword;
    }

    public void setCustomerAccessPassword(String customerAccessPassword) {
        this.customerAccessPassword = customerAccessPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(getCustomerLogin(), that.getCustomerLogin()) &&
                Objects.equals(getCustomerURL(), that.getCustomerURL()) &&
                Objects.equals(getCustomerAccessLogin(), that.getCustomerAccessLogin()) &&
                Objects.equals(getCustomerAccessPassword(), that.getCustomerAccessPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getCustomerLogin(), getCustomerURL(), getCustomerAccessLogin(), getCustomerAccessPassword());
    }

    @Override
    public String toString() {
        return CustomerEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", customerLogin='" + customerLogin + '\'' +
                ", customerURL='" + customerURL + '\'' +
                ", customerAccessLogin='" + customerAccessLogin + '\'' +
                ", customerAccessPassword='" + customerAccessPassword;
    }
}
