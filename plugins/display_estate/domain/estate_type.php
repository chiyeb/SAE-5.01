<?php

namespace display_estate\domain;

/**
 * Classe EstateType
 *
 * Représente un type de bien immobilier, composé d’un code (entier) et d’un nom (chaîne de caractères).
 * Par exemple : code = 1, name = "Appartement"
 */
class EstateType
{
    /**
     * @var int $code Code numérique représentant un type de bien (ex: 1, 2, 3, ...)
     */
    private int $code;

    /**
     * @var string $name Nom du type de bien (ex: "Maison", "Appartement", etc.)
     */
    private string $name;

    /**
     * Constructeur de la classe EstateType
     *
     * @param int    $code Code numérique représentant le type de bien
     * @param string $name Nom du type de bien (ex: "Maison", "Appartement")
     */
    public function __construct($code, $name)
    {
        $this->code = $code;
        $this->name = $name;
    }

    /**
     * Récupère le code du type de bien
     *
     * @return int Code numérique (ex: 1)
     */
    public function getCode(): int
    {
        return $this->code;
    }

    /**
     * Récupère le nom du type de bien
     *
     * @return string Nom (ex: "Maison")
     */
    public function getName(): string
    {
        return $this->name;
    }
}
