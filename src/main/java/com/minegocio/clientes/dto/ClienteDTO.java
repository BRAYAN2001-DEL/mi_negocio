package com.minegocio.clientes.dto;

public class ClienteDTO {
    public  Long id;
    public  String identificationType;
    public  String identificationNumber;
    public  String names;
    public  String email;
    public  String cellphone;
    public  String mainProvince;
    public  String mainCity;
    public  String mainAddress;

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para identificationType
    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    // Getter y Setter para identificationNumber
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    // Getter y Setter para names
    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    // Getter y Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter y Setter para cellphone
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    // Getter y Setter para mainProvince
    public String getMainProvince() {
        return mainProvince;
    }

    public void setMainProvince(String mainProvince) {
        this.mainProvince = mainProvince;
    }

    // Getter y Setter para mainCity
    public String getMainCity() {
        return mainCity;
    }

    public void setMainCity(String mainCity) {
        this.mainCity = mainCity;
    }

    // Getter y Setter para mainAddress
    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }
}
