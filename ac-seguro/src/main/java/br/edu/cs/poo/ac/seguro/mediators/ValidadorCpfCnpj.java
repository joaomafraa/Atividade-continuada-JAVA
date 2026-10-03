package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {

    public static boolean ehCpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || !StringUtils.temSomenteNumeros(cpf)) {
            return false;
        }

        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            int numero = cpf.charAt(i) - '0';
            soma = soma + numero * peso;
            peso--;
        }

        int resto = soma % 11;
        int digito1;

        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        if (digito1 != cpf.charAt(9) - '0') {
            return false;
        }

        soma = 0;
        peso = 11;

        for (int i = 0; i < 10; i++) {
            int numero = cpf.charAt(i) - '0';
            soma = soma + numero * peso;
            peso--;
        }

        resto = soma % 11;
        int digito2;

        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        return digito2 == cpf.charAt(10) - '0';
    }

    public static boolean ehCnpjValido(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !StringUtils.temSomenteNumeros(cnpj)) {
            return false;
        }

        int soma = 0;
        int peso = 5;

        for (int i = 0; i < 12; i++) {
            int numero = cnpj.charAt(i) - '0';
            soma = soma + numero * peso;
            peso--;

            if (peso == 1) {
                peso = 9;
            }
        }

        int resto = soma % 11;
        int digito1;

        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        if (digito1 != cnpj.charAt(12) - '0') {
            return false;
        }

        soma = 0;
        peso = 6;

        for (int i = 0; i < 13; i++) {
            int numero = cnpj.charAt(i) - '0';
            soma = soma + numero * peso;
            peso--;

            if (peso == 1) {
                peso = 9;
            }
        }

        resto = soma % 11;
        int digito2;

        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        return digito2 == cnpj.charAt(13) - '0';
    }
}