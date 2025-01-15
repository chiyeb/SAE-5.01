<?php

namespace display_estate\domain;

class City
{
    private string $postal_code;
    private string $name;

    public function __construct($postal_code, $name)
    {
        $this->postal_code = $postal_code;
        $this->name = $name;
    }

    public function getPostalCode(): string
    {
        return $this->postal_code;
    }

    public function getName(): string
    {
        return $this->name;
    }


}