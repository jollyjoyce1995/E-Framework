/*
 * Joachim Grüneis
 * Copyright (C) 2013
 * All rights reserved.
 */
package at.grueneis.spengergasse.lesson_plan.persistence.jdbc;

import at.binarycheese.binaryeframework.StatementExcecution.PersistableBindAndMap;
import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * "template pattern"
 */
public abstract class AbstractDatabaseDao<T extends BasePersistable> implements DatabaseDao<T>, PersistableBindAndMap<T> {
}