<?php

namespace display_estate\domain;

/**
 * Classe City
 *
 * Représente une ville avec un code postal et un nom.
 * Peut être utilisée pour manipuler des données de localisation.
 */
class City
{
    /**
     * @var string $postal_code Code postal de la ville
     */
    private string $postal_code;

    /**
     * @var string $name Nom de la ville
     */
    private string $name;

    /**
     * Constructeur de la classe City
     *
     * @param string $postal_code Code postal de la ville (ex: "75001")
     * @param string $name Nom de la ville (ex: "Paris")
     */
    public function __construct($postal_code, $name)
    {
        $this->postal_code = $postal_code;
        $this->name = $name;
    }

    /**
     * Récupère le code postal de la ville
     *
     * @return string Code postal
     */
    public function getPostalCode(): string
    {
        return $this->postal_code;
    }

    /**
     * Récupère le nom de la ville
     *
     * @return string Nom de la ville
     */
    public function getName(): string
    {
        return $this->name;
    }
}
