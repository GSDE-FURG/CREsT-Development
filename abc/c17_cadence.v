// Benchmark "C17.blif" written by ABC on Thu Jun 15 02:23:22 2017

module C17.blif ( 
    \1GAT(0) , \2GAT(1) , \3GAT(2) , \6GAT(3) , \7GAT(4) ,
    \22GAT(10) , \23GAT(9)   );
  input  \1GAT(0) , \2GAT(1) , \3GAT(2) , \6GAT(3) , \7GAT(4) ;
  output \22GAT(10) , \23GAT(9) ;
  wire n7, n8, n9, n11;
  INVX1    g0(.A(\2GAT(1) ), .Y(n7));
  NAND2X1  g1(.A(\3GAT(2) ), .B(\1GAT(0) ), .Y(n8));
  AND2X1   g2(.A(\6GAT(3) ), .B(\3GAT(2) ), .Y(n9));
  OAI21X1  g3(.A0(n9), .A1(n7), .B0(n8), .Y(\22GAT(10) ));
  INVX1    g4(.A(\7GAT(4) ), .Y(n11));
  AOI21X1  g5(.A0(n11), .A1(n7), .B0(n9), .Y(\23GAT(9) ));
endmodule


