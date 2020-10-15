module fooCircuit (
    g0, g1, w1, w2, out0 );
  input  g0, g1;
  output w1, out0;
  wire w2;
  
  AND2X1    G000(.A(g0), .B(g1), .Y(w1));
  INVX1     G001(.A(w1), .Y(w2));
  NAND2X1   G002(.A(w2), .B(g1), .Y(out0));

endmodule


