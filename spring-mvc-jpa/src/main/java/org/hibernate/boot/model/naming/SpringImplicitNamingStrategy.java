package org.hibernate.boot.model.naming;

public class SpringImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    public SpringImplicitNamingStrategy() {
    }

    public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
        String name = source.getOwningPhysicalTableName() + "_" + source.getAssociationOwningAttributePath().getProperty();
        return this.toIdentifier(name, source.getBuildingContext());
    }
}