module CircuitoMinas (
    iA, iB, S, Cout );
  input  iA, iB;
  output S, Cout ;
  wire w1, w2, w3;

  
  NAND2X1   G000(.A(iA), .B(iB), .Y(w1));

  NAND2X1   G001(.A(iA), .B(w1), .Y(w2));  
  NAND2X1   G002(.A(w1), .B(iB), .Y(w3));

  NAND2X1   G003(.A(w2), .B(w3), .Y(S));
  NAND2X1   G004(.A(w3), .B(iB), .Y(Cout));


endmodule


