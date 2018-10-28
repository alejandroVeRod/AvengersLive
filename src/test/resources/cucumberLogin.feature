Feature: Acceder al Sistema

Scenario: El usuario esta registrado 
Given El usuario accede al sistema
When Coinciden email y contrasena
Then Accedera al sistema

Scenario: El usuario no esta registrado
Given El usuario va a acceder al sistema
When No esta registrado
Then Mostrara mensaje de email o contrasena incorrecta

Scenario: La contrasena del usuario es incorrecta
Given El usuario quiere acceder al sistema
When No coincide la contrasena del usuario con su email
Then Mostrara mensaje de email o contrasena incorrecta
      

      
      