Feature: consultar información
  Como Administrador de la biblioteca PH
  Quiero consultar la información registrada
  Para gestionar y tener control de la misma

  Background:
    Given que me encuentro en el sitio web como administrador

  Scenario: verificar el título
    When se obtiene el título del libro con ID '1'
    Then el título contiene mas de 4 letras

  Scenario: conocer el total de libros prestados
    When consulto la cantidad de libros prestados
    Then mostrará que el total es de 100 registros
