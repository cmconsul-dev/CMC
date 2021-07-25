package cmc.gflottepro;

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
            .importPackages("cmc.gflottepro");

        noClasses()
            .that()
            .resideInAnyPackage("cmc.gflottepro.service..")
            .or()
            .resideInAnyPackage("cmc.gflottepro.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..cmc.gflottepro.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
