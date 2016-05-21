package com.geowarin.hibernate.jpa.standalone.model;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

/**
 * @author elvis
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 5/18/16 6:09 PM
 */

@Entity
@NamedNativeQuery(name="User&Car", query="SELECT u.id FROM users u INNER JOIN cars c ON u.car_id = c.id")
@SqlResultSetMapping(name="UserCar",
	columns = { @ColumnResult( name = "id")})
public class UserWithCarView {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
