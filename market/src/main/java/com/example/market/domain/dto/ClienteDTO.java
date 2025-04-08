package com.example.market.domain.dto;

public class ClienteDTO {
    private Long id;
    private String name;      // antes: nombre
    private String email;
    private String address;   // antes: direccion
    private String phone;     // antes: telefono

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { // antes: getNombre()
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() { // antes: getDireccion()
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() { // antes: getTelefono()
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
