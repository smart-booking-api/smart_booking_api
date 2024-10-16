package com.smart.booking.common.annotations;

/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@IdGeneratorType(com.smart.booking.common.util.TsidGenerator.class)
@ValueGenerationType(generatedBy = com.smart.booking.common.util.TsidGenerator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface TsidGenerator {

}
