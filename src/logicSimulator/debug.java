package logicSimulator;

import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class debug {
    public static void main(String[] args) throws Exception {
        boolean append = true;
        FileHandler handler = new FileHandler("default.log", append);

        Logger logger = Logger.getLogger("br.com.MinhaClasse");
        logger.addHandler(handler);

        logger.info("Iniciando procedimentos");
        //Venda venda = new Venda();
        logger.info("Criando novo objeto venda");
        //venda.setNumeroOcorrencia("1234");
        //venda.setObs("MINHA VENDA XX");
        //VendaDao vendaDao = new VendaDao();
        //vendaDao.save(venda);
        logger.info("venda salvo no banco com sucesso");

    }
}
