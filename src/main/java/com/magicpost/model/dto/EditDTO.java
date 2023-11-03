package com.magicpost.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditDTO {
    private long id;
    private String name;
    private long idAccount;
    private String password;

}
