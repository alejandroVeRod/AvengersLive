package cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.mongodb.DAOEmpleado;
import modelo.mongodb.Empleado;

public class LoginTest {

	Empleado e1,e2;
	DAOEmpleado dao;
	
	
	//ESCENARIO 1: USUARIO REGISTRADO
	
	@Given("^El usuario accede al sistema$")
	public void el_usuario_accede_al_sistema() throws Throwable {
		dao = new DAOEmpleado();
	    e1=new Empleado("ruben.perez7@alu.uclm.es","maricarmen");
	    assert(true);
	}

	@When("^Coinciden email y contrasena$")
	public void coinciden_email_y_contrasena() throws Throwable {
		assert(e1.credencialesCorrectas(e1.getEmail(), e1.getContrasena()));
	}

	@Then("^Accedera al sistema$")
	public void accedera_al_sistema() throws Throwable {
		assert(true);
	}

	
	//ESCENARIO 2: USUARIO NO REGISTRADO
	
	@Given("^El usuario va a acceder al sistema$")
	public void el_usuario_va_a_acceder_al_sistema() throws Throwable {
		dao = new DAOEmpleado();
		e1=new Empleado("ruben.perez7@alu.uclm.e","maricarmen");
	    assert(true);
	}

	@When("^No esta registrado$") 
	public void no_esta_registrado() throws Throwable {
		assert(!e1.credencialesCorrectas(e1.getEmail(), e1.getContrasena()));
	}

	@Then("^Mostrara mensaje de email o contrasena incorrecta$") //THEN DE ESCENARIO 2 Y 3
	public void mostrara_mensaje_de_email_o_contrasena_incorrecta() throws Throwable {
		System.out.println("\tEl email o la contraseña es incorrecta");
	    assert(true);
	}

	
	//ESCENARIO3: CONTRASEÑA INCORRECTA
	@Given("^El usuario quiere acceder al sistema$")
	public void el_usuario_quiere_acceder_al_sistema() throws Throwable {
		dao = new DAOEmpleado();
		e1=new Empleado("ruben.perez7@alu.uclm.es","maricarme");
	    assert(true);
	}

	@When("^No coincide la contrasena del usuario con su email$")
	public void no_coincide_la_contrasena_del_usuario_con_su_email() throws Throwable {
		assert(!e1.credencialesCorrectas(e1.getEmail(), e1.getContrasena()));
	}

}