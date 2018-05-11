package com.univalle.parcial.parcial.modelo;

@SuppressWarnings("serial")
public class ValidarCampos{

    boolean valor;

    public ValidarCampos() {

    }
    public Boolean Email(String Email){


        if(Email.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$") &&(!(Email.equals("")))){

            valor=true;
        }
        else{
            valor=false;
        }

        return valor;
    }

    public Boolean Alfanumerico(String alfa){
        try{
            if(alfa.equals("")){
                valor=false;
            }
            else if(alfa.matches("^[A-Za-zñÑ0-9]*")){
                valor=true;
            }
            else{
                valor=false;
            }
        }
        catch(Exception e){
            valor=false;
        }
        return valor;
    }

    public Boolean AlfanumericoCaracteres(String alfc){
        try{
            if(alfc.matches("^[A-Za-zñÑ0-9.%#-_ ]*")){
                valor=true;
            }
            else{
                valor=false;
            }
        }
        catch(Exception e){
            System.out.println("ERROR "+e);
            valor=false;
        }
        return valor;
    }

    public Boolean Numero(String num){


        if(num.matches("^[0-9.]*") && !(num.equals(""))){
            valor=true;
            System.out.println(valor);
        }
        else{
            valor=false;
        }

        return valor;
    }

    public Boolean Texto(String text){

        if(text.matches("^[A-Za-á-é-í-ó-ú-zñÑ ]*")){
            valor=true;
        }
        else{
            valor=false;
        }
        return valor;
    }

}
