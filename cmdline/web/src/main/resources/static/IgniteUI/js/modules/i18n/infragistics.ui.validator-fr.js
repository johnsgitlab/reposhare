﻿/*!@license
* Infragistics.Web.ClientUI Validator localization resources 16.1.20161.1009
*
* Copyright (c) 2011-2016 Infragistics Inc.
*
* http://www.infragistics.com/
*
*/

/*global jQuery */
(function ($) {
    $.ig = $.ig || {};

    if (!$.ig.Validator) {
	    $.ig.Validator = {
		    locale: {
			    defaultMessage: 'Veuillez réparer ce champ',
			    selectMessage: 'Veuillez sélectionner une valeur',
			    rangeSelectMessage: 'Veuillez sélectionner au maximum {0} et au minimum {1} éléments',
			    minSelectMessage: 'Veuillez sélectionner au moins {0} éléments',
			    maxSelectMessage: 'Veuillez sélectionner au maximum {0} éléments',
			    rangeLengthMessage: 'Veuillez entrer une valeur contenant {0} à {1} caractères',
			    minLengthMessage: 'Veuillez entrer au moins {0} caractères',
			    maxLengthMessage: 'Veuillez sélectionner au maximum {0} caractères',
			    requiredMessage: 'Ce champ est obligatoire',
			    patternMessage: 'La valeur entrée ne correspond pas au schéma requis',
			    maskMessage: 'Veuillez remplir tous les postes requis',
			    dateFieldsMessage: 'Veuillez entrer des valeurs dans les champs de dates',
			    invalidDayMessage: 'Jour du mois invalide. Veuillez entrer un jour correct',
			    dateMessage: 'Veuillez entrer une date valide',
			    numberMessage: 'Veuillez entrer un nombre valide',
                rangeValueMessage: 'Veuillez entrer une valeur entre {0} et {1}',
		        minValueMessage: 'Veuillez entrer une valeur supérieure ou égale à {0}',
		        maxValueMessage: 'Veuillez entrer une valeur inférieure ou égale à {0}',
		        emailMessage: 'Une adresse e-mail valide doit être saisie',
		        equalToMessage: 'Les deux valeurs ne correspondent pas',
		        optionalString: '(facultatif)'
		    }
	    };
    }
})(jQuery);