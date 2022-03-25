# new feature
# Tags: optional

Feature: Realizar registro al sitio web de la biblioteca PH
  Como usuario de la biblioteca PH
  Quiero registrarme
  Para solicitar préstamos de libros

  Background:
    Given que el usuario se encuentra en la aplicación

  Scenario: registro con datos correctos
    When desea realizar el registro con email "eve.holt@reqres.in" y password "pistol"
    Then el registro es exitoso

  Scenario: registro con datos incompletos
    When el usuario intenta realizar el registro  solo con el email "eve.holt@reqres.in"
    Then el registro no es exitoso



