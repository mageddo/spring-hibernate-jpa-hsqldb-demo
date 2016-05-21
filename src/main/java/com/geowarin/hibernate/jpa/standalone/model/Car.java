package com.geowarin.hibernate.jpa.standalone.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author elvis
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 5/18/16 6:00 PM
 */
@Table(name = "cars")
@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

		@Column(name = "model", nullable = false, unique = true)
    private String model;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
