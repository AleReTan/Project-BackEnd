package ru.vsu.entity;

import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;

import java.util.Objects;

@ObjectTypeId(id = 10)
public class VendorEntity extends ObjectEntity {
    @ParamAttributeId(id = 27)
    private String vendorLogin;
    @ParamAttributeId(id = 28)
    private String vendorURL;
    @ParamAttributeId(id = 30)
    private String vendorAccessLogin;
    @ParamAttributeId(id = 31)
    private String vendorAccessPassword;

    public VendorEntity() {
    }

    public VendorEntity(long id, String name, long typeId, String vendorLogin, String vendorURL, String vendorAccessLogin, String vendorAccessPassword) {
        super(id, name, typeId);
        this.vendorLogin = vendorLogin;
        this.vendorURL = vendorURL;
        this.vendorAccessLogin = vendorAccessLogin;
        this.vendorAccessPassword = vendorAccessPassword;
    }

    public String getVendorLogin() {
        return vendorLogin;
    }

    public void setVendorLogin(String vendorLogin) {
        this.vendorLogin = vendorLogin;
    }

    public String getVendorURL() {
        return vendorURL;
    }

    public void setVendorURL(String vendorURL) {
        this.vendorURL = vendorURL;
    }

    public String getVendorAccessLogin() {
        return vendorAccessLogin;
    }

    public void setVendorAccessLogin(String vendorAccessLogin) {
        this.vendorAccessLogin = vendorAccessLogin;
    }

    public String getVendorAccessPassword() {
        return vendorAccessPassword;
    }

    public void setVendorAccessPassword(String vendorAccessPassword) {
        this.vendorAccessPassword = vendorAccessPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VendorEntity that = (VendorEntity) o;
        return Objects.equals(getVendorLogin(), that.getVendorLogin()) &&
                Objects.equals(getVendorURL(), that.getVendorURL()) &&
                Objects.equals(getVendorAccessLogin(), that.getVendorAccessLogin()) &&
                Objects.equals(getVendorAccessPassword(), that.getVendorAccessPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getVendorLogin(), getVendorURL(), getVendorAccessLogin(), getVendorAccessPassword());
    }

    @Override
    public String toString() {
        return VendorEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", vendorLogin='" + vendorLogin + '\'' +
                ", vendorURL='" + vendorURL + '\'' +
                ", vendorAccessLogin='" + vendorAccessLogin + '\'' +
                ", vendorAccessPassword='" + vendorAccessPassword;
    }
}
