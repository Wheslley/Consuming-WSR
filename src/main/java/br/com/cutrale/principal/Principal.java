package br.com.cutrale.principal;

import br.com.cutrale.controller.AuxStringXML;
import br.com.cutrale.controller.ConsomeWS;

/**
 *
 * @author Wheslley Nycolas da Silva - sc703450
 */
public class Principal {

    public static void main(String args[]) {
        
        AuxStringXML aux = new AuxStringXML();
        
        ConsomeWS consomeWS = new ConsomeWS();

        System.out.println("\n\n\n\n\n\nTESTE CONSUMIR WEB SERVICE - GET");
        consomeWS.consomeGetWS();

        System.out.println("\n\n\n\n\n\nTESTE CONSUMIR WEB SERVICE - POST");
        
        consomeWS.consomePostWS(aux.getXml().toString(), "SC703450", 39234);
    }

}
