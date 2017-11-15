package com.crypted2.estrelasdonorte.model;

import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class Member {
    private StringProperty name;
    private StringProperty abbreviation;
}
