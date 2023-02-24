#encoding: utf-8
from decimal import *
import os
import shutil

# ---------------------------------------------------------------------------  #    Funçôes
def Troca(leitura, copia, supply, subcircuito,time):

    metade = (supply / 2)
    s = (70 * supply) / 100
    w = (30 * supply) / 100

    w = Decimal(str(w)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)
    metade = Decimal(str(metade)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)
    s = Decimal(str(s)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)
    supply = Decimal(str(supply)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)

    cont = 0
    aux = 1
    for i in leitura:
        if (cont == 4):
            
			
            copia[0] = '*	Simulacao CMOS 16nm '+ str(subcircuito)+  '   '  + str(supply) + 'V\n'
            copia[5] = '        .param supply  = ' + str(supply) + '\n'
            copia[6] = '        .param tp = ' + str(metade) + '\n'
            copia[7] = '        .param ruido_s = ' + str(s) + '\n'
            copia[8] = '        .param ruido_i = ' + str(w) + '\n'
            copia[12] = '		.param time = ' + str(time) + 'n\n'
            copia[65+aux] = '                                          X1v inversor3 inversor4 xor vdd3 gnd vdd3 gnd ' + str(subcircuito) + '\n'


            copia[91] = '      meas tran tphl_ab1 trig v(inversor3) val = ' + str(metade) + '  rise = 1 targ v(xor) val = ' + str(metade) + ' fall = 1  '+ '\n'
            copia[92] = '      meas tran tplh_ab1 trig v(inversor3) val = ' + str(metade) + '   fall = 1 targ v(xor) val =' + str(metade) + ' rise = 3 \n'
            copia[93] = '      meas tran tplh_ab0 trig v(inversor3) val = ' + str(metade) + '   rise = 2  targ v(xor) val =' + str(metade) + ' rise = 4 \n'
            copia[94] = '      meas tran tphl_ab0 trig v(inversor3) val = ' + str(metade) + '   fall = 2  targ v(xor) val = ' + str(metade) + ' fall = 4 \n'

            copia[112] = '     meas tran tplh_a0b trig v(inversor4) val = ' + str(metade) + '  rise = 1  targ v(xor) val =' + str(metade) + ' rise = 1 \n'
            copia[113] = '     meas tran tphl_a0b trig v(inversor4) val =' + str(metade) + '   fall = 2  targ v(xor) val =' + str(metade) + ' fall = 3 \n'
            copia[114] = '     meas tran tphl_a1b trig v(inversor4) val =' + str(metade) + ' fall = 1  targ v(xor) val =' + str(metade) + ' rise = 2  \n'
            copia[115] = '     meas tran tplh_a1b trig v(inversor4) val =' + str(metade) + '  rise = 2  targ v(xor) val =' + str(metade) + ' fall = 2 \n'

            #-----------------------------------------#              Nome do arquivo a ser salvo os resultados

            copia[126] = '	   echo tphl_ab1  $&tphl_ab1  >> '+str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[127] = '	   echo tplh_ab1  $&tplh_ab1  >> '+str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[128] = '	   echo tplh_ab0  $&tplh_ab0  >> '+str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[129] = '	   echo tphl_ab0  $&tphl_ab0  >> '+str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'

            copia[131] = '	   echo tplh_a0b  $&tplh_a0b    >> ' +str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[132] = '	   echo tphl_a0b  $&tphl_a0b    >> ' +str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[133] = '	   echo tphl_a1b  $&tphl_a1b    >> ' +str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[134] = '	   echo tplh_a1b  $&tplh_a1b    >> ' +str(subcircuito) +'/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[135] = '     echo energia2  $&energia2    >> ' + str(subcircuito) + '/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'
            copia[136] = '     echo energia2  $&energia3    >> ' + str(subcircuito) + '/Resultado_' + str(subcircuito) + '_' + str(supply) + '_CMOS_16nm.txt \n'

            copia[162] = '.tran 0.001n \'9* time\'' + '\n'
			  #\"conteudo\"
            print('Circuito em conversão --------> '+ str(subcircuito) + '\n')

        cont = cont + 1



def diretorio (subcircuito):

		diretorio = str(subcircuito)

		os.mkdir (diretorio)



def copia_bibliotecas_16nm(pasta):

    fonte = '16nm_HP.pm'
    shutil.copy(fonte,pasta)
    fonte = 'LIBRARY_CMOS_16nm.cir.txt'
    shutil.copy(fonte,pasta)
    #fonte = 'libfftw3-3.dll'
    #shutil.copy(fonte,pasta)
    #fonte = 'vcomp100.dll'
    #shutil.copy(fonte , pasta)



def Salva_Resultados (leitura, copia, voltagem_inicial ,subcircuito):

    contador = voltagem_inicial

    contador = Decimal(str(contador)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)

    pasta = str(subcircuito) + '/'

    copia_bibliotecas_16nm(subcircuito)

    while (contador >= 0.3):

        nome_arquivo2 =  str(subcircuito) + '_' + str(contador) + 'V_16nm' + '.txt'

        if (contador == voltagem_inicial):

            os.system("ngspice Xor_fonte_16nm.txt")

        else:

            os.system("ngspice " + pasta + nome_arquivo2)

        contador = (contador * 90) / 100

        contador = Decimal(str(contador)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)

    print('Processo de gravar resultados completo ')
	


def Volume_de_arquivos(leitura, copia, voltagem_inicial ,subcircuito,time):

        contador = voltagem_inicial



        contador = Decimal(str(contador)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)

        diretorio(subcircuito)

        pasta = str(subcircuito) + '/'

        while (contador >= 0.3) :

                  nome_arquivo2 =  str(subcircuito)+'_'+ str(contador) + 'V_16nm' + '.txt'

                  arquivo_copia = open(pasta + nome_arquivo2, 'w')

                  Troca(leitura, copia, contador ,subcircuito,time)

                  arquivo_copia.writelines(leitura)

                  contador = (contador * 90) / 100

                  contador = Decimal(str(contador)).quantize(Decimal('1.000'), rounding=ROUND_DOWN)


        print("Processo de Conversão Completo !!!!!!!!!\n")


def mostra(arquivo):
    for i in arquivo:
        teste = arquivo.readline()
        print(teste)



# ----------------------------------------------------------#   Entrada do arquivo

def main():
    
    print('Bem vindo ao conversor (Voltage Scaling) em circuitos XOR 16nm\n')

    resposta = 'sim'

    flag = True
    time = 1

    if( flag == True):

        #resposta = input('Deseja converter algum circuito (Sim) ou (Não)\nDigite sua resposta:')

        if ((resposta == 'Sim') | (resposta == 'sim')) : # ----------------------------------------------------------#  Abrindo arquivo

            nome_arquivo = 'XOR_fonte_16nm.txt'

            voltagem_inicial  = 0.765

            subcircuito =  'xor_9_min' #input("Insira nome do subcircuito inicial (xor_9_min): ")

            arquivo = open(nome_arquivo, 'r')

            leitura = []

            copia = []

            leitura = arquivo.readlines()

            copia = leitura

            arquivo.close()

            Volume_de_arquivos(leitura, copia , voltagem_inicial , subcircuito, time)

            Salva_Resultados(leitura, copia , voltagem_inicial , subcircuito)

        else:
            flag = False


print ("------------------------Inicio-------------------------------")

main()



print ('------------------------Fim-------------------------------------')










