# coding: utf-8
import sys

a = "La cantidad de par&aacutemetros recibidos fue de %d" % (len(sys.argv))
b = ""
for i in range(1, len(sys.argv)):
    b += "%s " % (sys.argv[i])

cadena = """
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8' />
        <title>HOLA MUNDO</title>
    </head>
    <body>
        %s<br />
        Los par&aacutemetros fueron:
        %s
    </body>
</html>
""" % (a, b)
print (cadena)

