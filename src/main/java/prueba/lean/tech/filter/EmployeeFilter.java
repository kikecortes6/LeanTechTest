package prueba.lean.tech.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import prueba.lean.tech.entitys.Employee;

@Service
public class EmployeeFilter {

	public Specification<Employee> filterByEmployeeNameAndPositionName(final Optional<String> employeeName, final Optional<String> positionName) {

		return (root, criteriaQuery, criteriaBuilder) -> {
			final List<Predicate> predicates = new ArrayList<>();
			if (!employeeName.isPresent() && !positionName.isPresent()) {
				predicates.add(criteriaBuilder.equal(criteriaBuilder.literal(1), 1));
			}
			if (employeeName.isPresent()) {
				predicates.add(criteriaBuilder.equal(root.get("person").get("name"), employeeName.get()));
			}
			if (positionName.isPresent()) {
				predicates.add(criteriaBuilder.equal(root.get("position").get("name"), positionName.get()));
			}

			return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
		};

	}

}
