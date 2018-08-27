package by.shalukho.entity.items;

import by.shalukho.entity.AbstractNamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@MappedSuperclass
public class AbstractItemPropertyEntity extends AbstractNamedEntity {


}
