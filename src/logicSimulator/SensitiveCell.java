package logicSimulator;

import datastructures.Gate;

public class SensitiveCell {
        String cell_id;
        String input_vec;
        String sensitive_are;
        Gate cell;

        public SensitiveCell(String cell_id, String input_vec, String sensitive_area){
                this.cell_id = cell_id;
                this.input_vec = input_vec;
                this.sensitive_are = sensitive_area;
        }

        public String getCell_id() {
                return cell_id;
        }

        public String getInput_vec() {
                return input_vec;
        }

        public String getSensitive_are() {
                return sensitive_are;
        }

}
