package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.ResultSet;

import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;

public interface PersistableBindAndMap<T extends BasePersistable> {
	 T bind(ResultSet resultSet);
}