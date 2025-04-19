package com.minegocio.clientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    public Long id;
    public String identificationType;
    public String identificationNumber;
    public String names;
    public String email;
    public String cellphone;
    public String mainProvince;
    public String mainCity;
    public String mainAddress;
}
