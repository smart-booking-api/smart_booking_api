package com.smart.booking.common.util;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

import com.github.f4b6a3.tsid.TsidCreator;
import com.github.f4b6a3.tsid.TsidFactory;
import java.util.EnumSet;
import java.util.UUID;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.id.IdentifierGenerator;

public class TsidGenerator implements BeforeExecutionGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return TsidCreator.getTsid().toString();

    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }
}
