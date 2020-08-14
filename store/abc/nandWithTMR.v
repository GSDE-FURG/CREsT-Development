module nandWithTMR (
    inA, inB, out );
  input  inA, inB;
  output out ;
  wire w1, w2, w3;

  
  NAND2X1   U0(.A(inA), .B(inB), .Y(w1));
  NAND2X1   U1(.A(inA), .B(inB), .Y(w2));
  NAND2X1   U2(.A(inA), .B(inB), .Y(w3));

  VOTADOR   U3(.A(w1), .B(w2), .C(w3), .Y(out));

endmodule


