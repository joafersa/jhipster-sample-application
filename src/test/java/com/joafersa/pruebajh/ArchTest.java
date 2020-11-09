package com.joafersa.pruebajh;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.joafersa.pruebajh");

        noClasses()
            .that()
            .resideInAnyPackage("com.joafersa.pruebajh.service..")
            .or()
            .resideInAnyPackage("com.joafersa.pruebajh.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.joafersa.pruebajh.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
