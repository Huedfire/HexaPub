Feature: Validacion de campos



   Scenario: El usuario presiona submit sin llenar el campo mensaje.

      Given El usuario se encuentra en la pagina https://www.ultimateqa.com/filling-out-forms/
      When El usuario presiona el boton submit sin llenar ningun otro campo
      Then La aplicacion muestra un mensaje donde se menciona que debe de llenar los campos

   Scenario: El usuario presiona submit con todos los campos llenos
      Given El usuario se encuentra en la pagina https://www.ultimateqa.com/filling-out-forms/
      When El usuario coloca su nombre en el campo nombre
      And El usuario coloca una descripcion
      And El usuario coloca el resultado
      Then La aplicacion muestra un mensaje de registro exitoso





