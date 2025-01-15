<?php

$templates = array(
	array(
		'id'      => 'contact-form',
		'name'    => 'Simple Contact Form',
		
		'icon'    => '<svg width="20" height="16" viewBox="0 0 20 16" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M17 0H3C2.20435 0 1.44129 0.316071 0.87868 0.87868C0.316071 1.44129 0 2.20435 0 3V13C0 13.7956 0.316071 14.5587 0.87868 15.1213C1.44129 15.6839 2.20435 16 3 16H17C17.7956 16 18.5587 15.6839 19.1213 15.1213C19.6839 14.5587 20 13.7956 20 13V3C20 2.20435 19.6839 1.44129 19.1213 0.87868C18.5587 0.316071 17.7956 0 17 0ZM3 2H17C17.2652 2 17.5196 2.10536 17.7071 2.29289C17.8946 2.48043 18 2.73478 18 3L10 7.88L2 3C2 2.73478 2.10536 2.48043 2.29289 2.29289C2.48043 2.10536 2.73478 2 3 2ZM18 13C18 13.2652 17.8946 13.5196 17.7071 13.7071C17.5196 13.8946 17.2652 14 17 14H3C2.73478 14 2.48043 13.8946 2.29289 13.7071C2.10536 13.5196 2 13.2652 2 13V5.28L9.48 9.85C9.63202 9.93777 9.80446 9.98397 9.98 9.98397C10.1555 9.98397 10.328 9.93777 10.48 9.85L18 5.28V13Z" /></svg>',

		'desc'    => 'A simple contact form with fields for name, email, subject, and message.',
		'content' => "<!-- wp:wpzoom-forms/form -->\n<div class=\"wp-block-wpzoom-forms-form\"><!-- wp:group -->\n<div class=\"wp-block-group\"><!-- wp:columns -->\n<div class=\"wp-block-columns\"><!-- wp:column {\"width\":\"100%\"} -->\n<div class=\"wp-block-column\" style=\"flex-basis:100%\"><!-- wp:wpzoom-forms/text-name-field {\"id\":\"input_name\",\"name\":\"Name\",\"label\":\"Name\",\"className\":\"fullwidth\"} -->\n<label for=\"input_name\"><label for=\"input_name\">Name</label><sup class=\"wp-block-wpzoom-forms-required\">*</sup></label><input type=\"text\" name=\"input_name\" id=\"input_name\" placeholder=\"\" required class=\"wp-block-wpzoom-forms-text-name-field fullwidth\"/>\n<!-- /wp:wpzoom-forms/text-name-field -->\n\n<!-- wp:wpzoom-forms/text-email-field {\"id\":\"input_email\",\"name\":\"Email\",\"label\":\"Email\",\"replyto\":true,\"className\":\"fullwidth\"} -->\n<label for=\"input_email\"><label for=\"input_email\">Email</label><sup class=\"wp-block-wpzoom-forms-required\">*</sup></label><input type=\"email\" name=\"input_email\" id=\"input_email\" placeholder=\"\" required data-replyto=\"true\" class=\"wp-block-wpzoom-forms-text-email-field fullwidth\"/>\n<!-- /wp:wpzoom-forms/text-email-field -->\n\n<!-- wp:wpzoom-forms/text-plain-field {\"id\":\"input_subject\",\"name\":\"Subject\",\"label\":\"Subject\",\"subject\":true,\"className\":\"fullwidth\"} -->\n<label for=\"input_subject\"><label for=\"input_subject\">Subject</label><sup class=\"wp-block-wpzoom-forms-required\">*</sup></label><input type=\"text\" name=\"input_subject\" id=\"input_subject\" placeholder=\"\" required data-subject=\"true\" class=\"wp-block-wpzoom-forms-text-plain-field fullwidth\"/>\n<!-- /wp:wpzoom-forms/text-plain-field -->\n\n<!-- wp:wpzoom-forms/textarea-field {\"id\":\"input_message\",\"name\":\"Message\",\"label\":\"Message\",\"className\":\"fullwidth\"} -->\n<label for=\"input_message\"><label for=\"input_message\">Message</label><sup class=\"wp-block-wpzoom-forms-required\">*</sup></label><textarea name=\"input_message\" id=\"input_message\" cols=\"55\" rows=\"10\" placeholder=\"\" required class=\"wp-block-wpzoom-forms-textarea-field fullwidth\"></textarea>\n<!-- /wp:wpzoom-forms/textarea-field --></div>\n<!-- /wp:column --></div>\n<!-- /wp:columns -->\n\n<!-- wp:columns -->\n<div class=\"wp-block-columns\"><!-- wp:column {\"width\":\"30%\"} -->\n<div class=\"wp-block-column\" style=\"flex-basis:30%\"><!-- wp:wpzoom-forms/submit-field {\"id\":\"input_submit\"} -->\n<input type=\"submit\" id=\"input_submit\" value=\"Submit\" class=\"wp-block-wpzoom-forms-submit-field\"/>\n<!-- /wp:wpzoom-forms/submit-field --></div>\n<!-- /wp:column -->\n\n<!-- wp:column {\"width\":\"70%\"} -->\n<div class=\"wp-block-column\" style=\"flex-basis:70%\"><!-- wp:paragraph {\"align\":\"right\",\"style\":{\"typography\":{\"fontSize\":16}}} -->\n<p class=\"has-text-align-right\" style=\"font-size:16px\">Fields marked with <strong class=\"has-accent-color has-text-color\">*</strong> are required.</p>\n<!-- /wp:paragraph --></div>\n<!-- /wp:column --></div>\n<!-- /wp:columns --></div>\n<!-- /wp:group --></div>\n<!-- /wp:wpzoom-forms/form -->"
	),
	array(
		'id'      => 'advanced-form',
		'name'    => 'Advanced Contact Form',
		
		'icon'    => '<svg width="24" height="24" viewBox="0 0 576 512" xmlns="http://www.w3.org/2000/svg"><path d="M208 256c35.35 0 64-28.65 64-64c0-35.35-28.65-64-64-64s-64 28.65-64 64C144 227.3 172.7 256 208 256zM464 232h-96c-13.25 0-24 10.75-24 24s10.75 24 24 24h96c13.25 0 24-10.75 24-24S477.3 232 464 232zM240 288h-64C131.8 288 96 323.8 96 368C96 376.8 103.2 384 112 384h192c8.836 0 16-7.164 16-16C320 323.8 284.2 288 240 288zM464 152h-96c-13.25 0-24 10.75-24 24s10.75 24 24 24h96c13.25 0 24-10.75 24-24S477.3 152 464 152zM512 32H64C28.65 32 0 60.65 0 96v320c0 35.35 28.65 64 64 64h448c35.35 0 64-28.65 64-64V96C576 60.65 547.3 32 512 32zM528 416c0 8.822-7.178 16-16 16H64c-8.822 0-16-7.178-16-16V96c0-8.822 7.178-16 16-16h448c8.822 0 16 7.178 16 16V416z"/></svg>',

		'desc'    => 'A contact form with additional address fields.',
		'content' => '<!-- wp:wpzoom-forms/form -->
<div class="wp-block-wpzoom-forms-form"><!-- wp:group -->
<div class="wp-block-group"><!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"100%"} -->
<div class="wp-block-column" style="flex-basis:100%"><!-- wp:paragraph -->
<p><strong>Your Name</strong></p>
<!-- /wp:paragraph -->

<!-- wp:columns {"style":{"spacing":{"blockGap":{"top":"0","left":"var:preset|spacing|40"}}}} -->
<div class="wp-block-columns"><!-- wp:column {"width":"","style":{"spacing":{"padding":{"right":"var:preset|spacing|40"}}}} -->
<div class="wp-block-column" style="padding-right:var(--wp--preset--spacing--40)"><!-- wp:wpzoom-forms/text-name-field {"id":"input_name","name":"First","label":"First","style":{"spacing":{"margin":{"left":"0px"}}},"className":"fullwidth"} -->
<label for="input_name"><label for="input_name">First</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_name" id="input_name" placeholder="" required class="wp-block-wpzoom-forms-text-name-field fullwidth" style="margin-left:0px"/>
<!-- /wp:wpzoom-forms/text-name-field --></div>
<!-- /wp:column -->

<!-- wp:column -->
<div class="wp-block-column"><!-- wp:wpzoom-forms/text-plain-field {"id":"input_44c5e8a3","name":"Last","label":"Last"} -->
<label for="input_44c5e8a3"><label for="input_44c5e8a3">Last</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_44c5e8a3" id="input_44c5e8a3" placeholder="" required data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:wpzoom-forms/text-email-field {"id":"input_email","name":"Email","label":"Email","replyto":true,"className":"fullwidth"} -->
<label for="input_email"><label for="input_email">Email</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="email" name="input_email" id="input_email" placeholder="" required data-replyto="true" class="wp-block-wpzoom-forms-text-email-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-email-field -->

<!-- wp:wpzoom-forms/text-phone-field {"id":"input_1bb8d86c","name":"Phone number","label":"Phone number"} -->
<label for="input_1bb8d86c"><label for="input_1bb8d86c">Phone number</label></label><input type="tel" name="input_1bb8d86c" id="input_1bb8d86c" placeholder="" class="wp-block-wpzoom-forms-text-phone-field"/>
<!-- /wp:wpzoom-forms/text-phone-field -->

<!-- wp:spacer {"height":"40px"} -->
<div style="height:40px" aria-hidden="true" class="wp-block-spacer"></div>
<!-- /wp:spacer -->

<!-- wp:paragraph -->
<p><strong>Your Address</strong></p>
<!-- /wp:paragraph -->

<!-- wp:wpzoom-forms/text-plain-field {"id":"input_63de4a79","name":"Street address","label":"Street address"} -->
<label for="input_63de4a79"><label for="input_63de4a79">Street address</label></label><input type="text" name="input_63de4a79" id="input_63de4a79" placeholder="" data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field -->

<!-- wp:wpzoom-forms/text-plain-field {"id":"input_c2f48087","name":"Address Line 2","label":"Address Line 2"} -->
<label for="input_c2f48087"><label for="input_c2f48087">Address Line 2</label></label><input type="text" name="input_c2f48087" id="input_c2f48087" placeholder="" data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field -->

<!-- wp:columns {"style":{"spacing":{"blockGap":{"left":"var:preset|spacing|small"}}}} -->
<div class="wp-block-columns"><!-- wp:column {"style":{"spacing":{"padding":{"right":"var:preset|spacing|40"}}}} -->
<div class="wp-block-column" style="padding-right:var(--wp--preset--spacing--40)"><!-- wp:wpzoom-forms/text-plain-field {"id":"input_aded2b39","name":"City","label":"City"} -->
<label for="input_aded2b39"><label for="input_aded2b39">City</label></label><input type="text" name="input_aded2b39" id="input_aded2b39" placeholder="" data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field --></div>
<!-- /wp:column -->

<!-- wp:column -->
<div class="wp-block-column"><!-- wp:wpzoom-forms/text-plain-field {"id":"input_5d19b773","name":"ZIP Code","label":"ZIP Code"} -->
<label for="input_5d19b773"><label for="input_5d19b773">ZIP Code</label></label><input type="text" name="input_5d19b773" id="input_5d19b773" placeholder="" data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:columns {"style":{"spacing":{"blockGap":{"left":"var:preset|spacing|small"}}}} -->
<div class="wp-block-columns"><!-- wp:column {"style":{"spacing":{"padding":{"right":"var:preset|spacing|40"}}}} -->
<div class="wp-block-column" style="padding-right:var(--wp--preset--spacing--40)"><!-- wp:wpzoom-forms/select-field {"id":"input_3c1eb2cc","name":"State","options":["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"],"label":"State"} -->
<label for="input_3c1eb2cc"><label for="input_3c1eb2cc">State</label></label><select name="input_3c1eb2cc" id="input_3c1eb2cc" defaultvalue="Item #1" class="wp-block-wpzoom-forms-select-field"><option value="Alabama">Alabama</option><option value="Alaska">Alaska</option><option value="Arizona">Arizona</option><option value="Arkansas">Arkansas</option><option value="California">California</option><option value="Colorado">Colorado</option><option value="Connecticut">Connecticut</option><option value="Delaware">Delaware</option><option value="Florida">Florida</option><option value="Georgia">Georgia</option><option value="Hawaii">Hawaii</option><option value="Idaho">Idaho</option><option value="Illinois">Illinois</option><option value="Indiana">Indiana</option><option value="Iowa">Iowa</option><option value="Kansas">Kansas</option><option value="Kentucky">Kentucky</option><option value="Louisiana">Louisiana</option><option value="Maine">Maine</option><option value="Maryland">Maryland</option><option value="Massachusetts">Massachusetts</option><option value="Michigan">Michigan</option><option value="Minnesota">Minnesota</option><option value="Mississippi">Mississippi</option><option value="Missouri">Missouri</option><option value="Montana">Montana</option><option value="Nebraska">Nebraska</option><option value="Nevada">Nevada</option><option value="New Hampshire">New Hampshire</option><option value="New Jersey">New Jersey</option><option value="New Mexico">New Mexico</option><option value="New York">New York</option><option value="North Carolina">North Carolina</option><option value="North Dakota">North Dakota</option><option value="Ohio">Ohio</option><option value="Oklahoma">Oklahoma</option><option value="Oregon">Oregon</option><option value="Pennsylvania">Pennsylvania</option><option value="Rhode Island">Rhode Island</option><option value="South Carolina">South Carolina</option><option value="South Dakota">South Dakota</option><option value="Tennessee">Tennessee</option><option value="Texas">Texas</option><option value="Utah">Utah</option><option value="Vermont">Vermont</option><option value="Virginia">Virginia</option><option value="Washington">Washington</option><option value="West Virginia">West Virginia</option><option value="Wisconsin">Wisconsin</option><option value="Wyoming">Wyoming</option></select>
<!-- /wp:wpzoom-forms/select-field --></div>
<!-- /wp:column -->

<!-- wp:column -->
<div class="wp-block-column"><!-- wp:wpzoom-forms/select-field {"id":"input_e6c773f3","name":"Country","options":["Afghanistan","Albania","Algeria","Andorra","Angola","Antigua \u0026 Deps","Argentina","Armenia","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Central African Rep","Chad","Chile","China","Colombia","Comoros","Congo","Congo (Democratic Rep)","Costa Rica","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","East Timor","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Fiji","Finland","France","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland {Republic}","Israel","Italy","Ivory Coast","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","Korea North","Korea South","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar, {Burma}","Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Romania","Russian Federation","Rwanda","St Kitts \u0026 Nevis","St Lucia","Saint Vincent \u0026 the Grenadines","Samoa","San Marino","Sao Tome \u0026 Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Togo","Tonga","Trinidad \u0026 Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe"],"label":"Country"} -->
<label for="input_e6c773f3"><label for="input_e6c773f3">Country</label></label><select name="input_e6c773f3" id="input_e6c773f3" defaultvalue="Item #1" class="wp-block-wpzoom-forms-select-field"><option value="Afghanistan">Afghanistan</option><option value="Albania">Albania</option><option value="Algeria">Algeria</option><option value="Andorra">Andorra</option><option value="Angola">Angola</option><option value="Antigua &amp; Deps">Antigua &amp; Deps</option><option value="Argentina">Argentina</option><option value="Armenia">Armenia</option><option value="Australia">Australia</option><option value="Austria">Austria</option><option value="Azerbaijan">Azerbaijan</option><option value="Bahamas">Bahamas</option><option value="Bahrain">Bahrain</option><option value="Bangladesh">Bangladesh</option><option value="Barbados">Barbados</option><option value="Belarus">Belarus</option><option value="Belgium">Belgium</option><option value="Belize">Belize</option><option value="Benin">Benin</option><option value="Bhutan">Bhutan</option><option value="Bolivia">Bolivia</option><option value="Bosnia Herzegovina">Bosnia Herzegovina</option><option value="Botswana">Botswana</option><option value="Brazil">Brazil</option><option value="Brunei">Brunei</option><option value="Bulgaria">Bulgaria</option><option value="Burkina">Burkina</option><option value="Burundi">Burundi</option><option value="Cambodia">Cambodia</option><option value="Cameroon">Cameroon</option><option value="Canada">Canada</option><option value="Cape Verde">Cape Verde</option><option value="Central African Rep">Central African Rep</option><option value="Chad">Chad</option><option value="Chile">Chile</option><option value="China">China</option><option value="Colombia">Colombia</option><option value="Comoros">Comoros</option><option value="Congo">Congo</option><option value="Congo (Democratic Rep)">Congo (Democratic Rep)</option><option value="Costa Rica">Costa Rica</option><option value="Croatia">Croatia</option><option value="Cuba">Cuba</option><option value="Cyprus">Cyprus</option><option value="Czech Republic">Czech Republic</option><option value="Denmark">Denmark</option><option value="Djibouti">Djibouti</option><option value="Dominica">Dominica</option><option value="Dominican Republic">Dominican Republic</option><option value="East Timor">East Timor</option><option value="Ecuador">Ecuador</option><option value="Egypt">Egypt</option><option value="El Salvador">El Salvador</option><option value="Equatorial Guinea">Equatorial Guinea</option><option value="Eritrea">Eritrea</option><option value="Estonia">Estonia</option><option value="Ethiopia">Ethiopia</option><option value="Fiji">Fiji</option><option value="Finland">Finland</option><option value="France">France</option><option value="Gabon">Gabon</option><option value="Gambia">Gambia</option><option value="Georgia">Georgia</option><option value="Germany">Germany</option><option value="Ghana">Ghana</option><option value="Greece">Greece</option><option value="Grenada">Grenada</option><option value="Guatemala">Guatemala</option><option value="Guinea">Guinea</option><option value="Guinea-Bissau">Guinea-Bissau</option><option value="Guyana">Guyana</option><option value="Haiti">Haiti</option><option value="Honduras">Honduras</option><option value="Hungary">Hungary</option><option value="Iceland">Iceland</option><option value="India">India</option><option value="Indonesia">Indonesia</option><option value="Iran">Iran</option><option value="Iraq">Iraq</option><option value="Ireland {Republic}">Ireland {Republic}</option><option value="Israel">Israel</option><option value="Italy">Italy</option><option value="Ivory Coast">Ivory Coast</option><option value="Jamaica">Jamaica</option><option value="Japan">Japan</option><option value="Jordan">Jordan</option><option value="Kazakhstan">Kazakhstan</option><option value="Kenya">Kenya</option><option value="Kiribati">Kiribati</option><option value="Korea North">Korea North</option><option value="Korea South">Korea South</option><option value="Kosovo">Kosovo</option><option value="Kuwait">Kuwait</option><option value="Kyrgyzstan">Kyrgyzstan</option><option value="Laos">Laos</option><option value="Latvia">Latvia</option><option value="Lebanon">Lebanon</option><option value="Lesotho">Lesotho</option><option value="Liberia">Liberia</option><option value="Libya">Libya</option><option value="Liechtenstein">Liechtenstein</option><option value="Lithuania">Lithuania</option><option value="Luxembourg">Luxembourg</option><option value="Macedonia">Macedonia</option><option value="Madagascar">Madagascar</option><option value="Malawi">Malawi</option><option value="Malaysia">Malaysia</option><option value="Maldives">Maldives</option><option value="Mali">Mali</option><option value="Malta">Malta</option><option value="Marshall Islands">Marshall Islands</option><option value="Mauritania">Mauritania</option><option value="Mauritius">Mauritius</option><option value="Mexico">Mexico</option><option value="Micronesia">Micronesia</option><option value="Moldova">Moldova</option><option value="Monaco">Monaco</option><option value="Mongolia">Mongolia</option><option value="Montenegro">Montenegro</option><option value="Morocco">Morocco</option><option value="Mozambique">Mozambique</option><option value="Myanmar, {Burma}">Myanmar, {Burma}</option><option value="Namibia">Namibia</option><option value="Nauru">Nauru</option><option value="Nepal">Nepal</option><option value="Netherlands">Netherlands</option><option value="New Zealand">New Zealand</option><option value="Nicaragua">Nicaragua</option><option value="Niger">Niger</option><option value="Nigeria">Nigeria</option><option value="Norway">Norway</option><option value="Oman">Oman</option><option value="Pakistan">Pakistan</option><option value="Palau">Palau</option><option value="Panama">Panama</option><option value="Papua New Guinea">Papua New Guinea</option><option value="Paraguay">Paraguay</option><option value="Peru">Peru</option><option value="Philippines">Philippines</option><option value="Poland">Poland</option><option value="Portugal">Portugal</option><option value="Qatar">Qatar</option><option value="Romania">Romania</option><option value="Russian Federation">Russian Federation</option><option value="Rwanda">Rwanda</option><option value="St Kitts &amp; Nevis">St Kitts &amp; Nevis</option><option value="St Lucia">St Lucia</option><option value="Saint Vincent &amp; the Grenadines">Saint Vincent &amp; the Grenadines</option><option value="Samoa">Samoa</option><option value="San Marino">San Marino</option><option value="Sao Tome &amp; Principe">Sao Tome &amp; Principe</option><option value="Saudi Arabia">Saudi Arabia</option><option value="Senegal">Senegal</option><option value="Serbia">Serbia</option><option value="Seychelles">Seychelles</option><option value="Sierra Leone">Sierra Leone</option><option value="Singapore">Singapore</option><option value="Slovakia">Slovakia</option><option value="Slovenia">Slovenia</option><option value="Solomon Islands">Solomon Islands</option><option value="Somalia">Somalia</option><option value="South Africa">South Africa</option><option value="South Sudan">South Sudan</option><option value="Spain">Spain</option><option value="Sri Lanka">Sri Lanka</option><option value="Sudan">Sudan</option><option value="Suriname">Suriname</option><option value="Swaziland">Swaziland</option><option value="Sweden">Sweden</option><option value="Switzerland">Switzerland</option><option value="Syria">Syria</option><option value="Taiwan">Taiwan</option><option value="Tajikistan">Tajikistan</option><option value="Tanzania">Tanzania</option><option value="Thailand">Thailand</option><option value="Togo">Togo</option><option value="Tonga">Tonga</option><option value="Trinidad &amp; Tobago">Trinidad &amp; Tobago</option><option value="Tunisia">Tunisia</option><option value="Turkey">Turkey</option><option value="Turkmenistan">Turkmenistan</option><option value="Tuvalu">Tuvalu</option><option value="Uganda">Uganda</option><option value="Ukraine">Ukraine</option><option value="United Arab Emirates">United Arab Emirates</option><option value="United Kingdom">United Kingdom</option><option value="United States">United States</option><option value="Uruguay">Uruguay</option><option value="Uzbekistan">Uzbekistan</option><option value="Vanuatu">Vanuatu</option><option value="Vatican City">Vatican City</option><option value="Venezuela">Venezuela</option><option value="Vietnam">Vietnam</option><option value="Yemen">Yemen</option><option value="Zambia">Zambia</option><option value="Zimbabwe">Zimbabwe</option></select>
<!-- /wp:wpzoom-forms/select-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:spacer {"height":"40px"} -->
<div style="height:40px" aria-hidden="true" class="wp-block-spacer"></div>
<!-- /wp:spacer -->

<!-- wp:wpzoom-forms/text-plain-field {"id":"input_subject","name":"Subject","label":"Subject","subject":true,"className":"fullwidth"} -->
<label for="input_subject"><label for="input_subject">Subject</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_subject" id="input_subject" placeholder="" required data-subject="true" class="wp-block-wpzoom-forms-text-plain-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-plain-field -->

<!-- wp:wpzoom-forms/textarea-field {"id":"input_message","name":"Message","label":"Message","className":"fullwidth"} -->
<label for="input_message"><label for="input_message">Message</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><textarea name="input_message" id="input_message" cols="55" rows="10" placeholder="" required class="wp-block-wpzoom-forms-textarea-field fullwidth"></textarea>
<!-- /wp:wpzoom-forms/textarea-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"30%"} -->
<div class="wp-block-column" style="flex-basis:30%"><!-- wp:wpzoom-forms/submit-field {"id":"input_submit"} -->
<input type="submit" id="input_submit" value="Submit" class="wp-block-wpzoom-forms-submit-field"/>
<!-- /wp:wpzoom-forms/submit-field --></div>
<!-- /wp:column -->

<!-- wp:column {"width":"70%"} -->
<div class="wp-block-column" style="flex-basis:70%"><!-- wp:paragraph {"align":"right","style":{"typography":{"fontSize":16}}} -->
<p class="has-text-align-right" style="font-size:16px">Fields marked with <strong class="has-accent-color has-text-color">*</strong> are required.</p>
<!-- /wp:paragraph --></div>
<!-- /wp:column --></div>
<!-- /wp:columns --></div>
<!-- /wp:group --></div>
<!-- /wp:wpzoom-forms/form -->'
	),
	array(
		'id'      => 'registration-form',
		'name'    => 'Wedding Invitation RSVP',
		
		'icon'    => '<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
		<path d="M14.58 11.3C15.0345 10.7319 15.2846 10.0275 15.29 9.3C15.29 8.42744 14.9434 7.59061 14.3264 6.97362C13.7094 6.35662 12.8726 6.01 12 6.01C11.1274 6.01 10.2906 6.35662 9.67362 6.97362C9.05662 7.59061 8.71 8.42744 8.71 9.3C8.71542 10.0275 8.96551 10.7319 9.42 11.3C8.52863 11.8424 7.8293 12.6502 7.42 13.61C7.36271 13.7318 7.3306 13.8639 7.32558 13.9984C7.32056 14.1329 7.34274 14.267 7.39079 14.3927C7.43884 14.5184 7.51177 14.6331 7.60521 14.73C7.69865 14.8268 7.81068 14.9038 7.93459 14.9563C8.0585 15.0089 8.19174 15.0358 8.32632 15.0357C8.4609 15.0355 8.59406 15.0081 8.71782 14.9552C8.84157 14.9023 8.95338 14.825 9.04655 14.7279C9.13971 14.6308 9.21231 14.5159 9.26 14.39C9.48983 13.8527 9.87157 13.3943 10.3583 13.071C10.8451 12.7477 11.4157 12.5735 12 12.57C12.586 12.5716 13.1588 12.7448 13.6475 13.0683C14.1362 13.3917 14.5195 13.8512 14.75 14.39C14.8266 14.5707 14.9546 14.725 15.1182 14.8334C15.2818 14.9419 15.4737 14.9998 15.67 15C15.8038 14.9975 15.936 14.9704 16.06 14.92C16.3039 14.8164 16.4966 14.6203 16.596 14.3747C16.6954 14.129 16.6932 13.854 16.59 13.61C16.1781 12.6487 15.4751 11.8408 14.58 11.3ZM12 10.57C11.7444 10.57 11.4946 10.4941 11.2823 10.3519C11.0699 10.2097 10.9046 10.0076 10.8073 9.77132C10.7099 9.53502 10.685 9.27514 10.7356 9.02463C10.7862 8.77413 10.91 8.5443 11.0914 8.36429C11.2728 8.18429 11.5036 8.06222 11.7545 8.01358C12.0054 7.96494 12.2651 7.99192 12.5006 8.09109C12.7362 8.19026 12.9369 8.35716 13.0775 8.57061C13.218 8.78406 13.292 9.03445 13.29 9.29C13.2874 9.63039 13.1503 9.95595 12.9087 10.1957C12.667 10.4355 12.3404 10.57 12 10.57ZM18 2H6C5.20435 2 4.44129 2.31607 3.87868 2.87868C3.31607 3.44129 3 4.20435 3 5V16C3 16.7956 3.31607 17.5587 3.87868 18.1213C4.44129 18.6839 5.20435 19 6 19H8.59L11.29 21.71C11.3834 21.8027 11.4943 21.876 11.6161 21.9258C11.7379 21.9755 11.8684 22.0008 12 22C12.2383 22 12.4689 21.9149 12.65 21.76L15.87 19H18C18.7956 19 19.5587 18.6839 20.1213 18.1213C20.6839 17.5587 21 16.7956 21 16V5C21 4.20435 20.6839 3.44129 20.1213 2.87868C19.5587 2.31607 18.7956 2 18 2ZM19 16C19 16.2652 18.8946 16.5196 18.7071 16.7071C18.5196 16.8946 18.2652 17 18 17H15.5C15.2617 17 15.0311 17.0851 14.85 17.24L12.05 19.64L9.71 17.29C9.61656 17.1973 9.50574 17.124 9.38391 17.0742C9.26207 17.0245 9.13161 16.9992 9 17H6C5.73478 17 5.48043 16.8946 5.29289 16.7071C5.10536 16.5196 5 16.2652 5 16V5C5 4.73478 5.10536 4.48043 5.29289 4.29289C5.48043 4.10536 5.73478 4 6 4H18C18.2652 4 18.5196 4.10536 18.7071 4.29289C18.8946 4.48043 19 4.73478 19 5V16Z" />
		</svg>',

		'desc'    => 'A simple form that can be used to invite people to a party or a wedding.',
		'content' => '<!-- wp:wpzoom-forms/form -->
<div class="wp-block-wpzoom-forms-form"><!-- wp:group -->
<div class="wp-block-group"><!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"100%"} -->
<div class="wp-block-column" style="flex-basis:100%"><!-- wp:wpzoom-forms/text-name-field {"id":"input_name","name":"Your Name","label":"Your Name","className":"fullwidth"} -->
<label for="input_name"><label for="input_name">Your Name</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_name" id="input_name" placeholder="" required class="wp-block-wpzoom-forms-text-name-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-name-field -->

<!-- wp:wpzoom-forms/text-email-field {"id":"input_email","name":"Email","label":"Email","replyto":true,"className":"fullwidth"} -->
<label for="input_email"><label for="input_email">Email</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="email" name="input_email" id="input_email" placeholder="" required data-replyto="true" class="wp-block-wpzoom-forms-text-email-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-email-field -->

<!-- wp:wpzoom-forms/radio-field {"id":"input_3cac9a4a","name":"Will you be attending?","options":["Yes","No"],"label":"Will you be attending?"} -->
<label for="input_3cac9a4a"><label for="input_3cac9a4a">Will you be attending?</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><ul class="wp-block-wpzoom-forms-radio-field"><li><label><input type="radio" name="input_3cac9a4a" id="input_3cac9a4a" value="Yes" required/>Yes</label></li><li><label><input type="radio" name="input_3cac9a4a" id="input_3cac9a4a" value="No" required/>No</label></li></ul>
<!-- /wp:wpzoom-forms/radio-field -->

<!-- wp:wpzoom-forms/select-field {"id":"input_15145568","name":"Your meal preference","options":["Omnivore","Vegetarian","Gluten Free","Kosher","Vegan","Halal"],"label":"Your meal preference"} -->
<label for="input_15145568"><label for="input_15145568">Your meal preference</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><select name="input_15145568" id="input_15145568" required defaultvalue="Item #1" class="wp-block-wpzoom-forms-select-field"><option value="Omnivore">Omnivore</option><option value="Vegetarian">Vegetarian</option><option value="Gluten Free">Gluten Free</option><option value="Kosher">Kosher</option><option value="Vegan">Vegan</option><option value="Halal">Halal</option></select>
<!-- /wp:wpzoom-forms/select-field -->

<!-- wp:wpzoom-forms/radio-field {"id":"input_7a40e84d","name":"Are you bringing a +1?","options":["Yes","No"],"label":"Are you bringing a +1?"} -->
<label for="input_7a40e84d"><label for="input_7a40e84d">Are you bringing a +1?</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><ul class="wp-block-wpzoom-forms-radio-field"><li><label><input type="radio" name="input_7a40e84d" id="input_7a40e84d" value="Yes" required/>Yes</label></li><li><label><input type="radio" name="input_7a40e84d" id="input_7a40e84d" value="No" required/>No</label></li></ul>
<!-- /wp:wpzoom-forms/radio-field -->

<!-- wp:wpzoom-forms/text-plain-field {"id":"input_0bbcc55a","name":"Your guest\'s name","label":"Your guest\'s name"} -->
<label for="input_0bbcc55a"><label for="input_0bbcc55a">Your guest\'s name</label></label><input type="text" name="input_0bbcc55a" id="input_0bbcc55a" placeholder="" data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field -->

<!-- wp:wpzoom-forms/select-field {"id":"input_15145568","name":"Your guest\'s meal preference","options":["Omnivore","Vegetarian","Gluten Free","Kosher","Vegan","Halal"],"label":"Your guest\'s meal preference"} -->
<label for="input_15145568"><label for="input_15145568">Your guest\'s meal preference</label></label><select name="input_15145568" id="input_15145568" defaultvalue="Item #1" class="wp-block-wpzoom-forms-select-field"><option value="Omnivore">Omnivore</option><option value="Vegetarian">Vegetarian</option><option value="Gluten Free">Gluten Free</option><option value="Kosher">Kosher</option><option value="Vegan">Vegan</option><option value="Halal">Halal</option></select>
<!-- /wp:wpzoom-forms/select-field -->

<!-- wp:wpzoom-forms/textarea-field {"id":"input_message","name":"Any comments or questions?","label":"Any comments or questions?","className":"fullwidth"} -->
<label for="input_message"><label for="input_message">Any comments or questions?</label></label><textarea name="input_message" id="input_message" cols="55" rows="10" placeholder="" class="wp-block-wpzoom-forms-textarea-field fullwidth"></textarea>
<!-- /wp:wpzoom-forms/textarea-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"30%"} -->
<div class="wp-block-column" style="flex-basis:30%"><!-- wp:wpzoom-forms/submit-field {"id":"input_submit"} -->
<input type="submit" id="input_submit" value="Submit" class="wp-block-wpzoom-forms-submit-field"/>
<!-- /wp:wpzoom-forms/submit-field --></div>
<!-- /wp:column -->

<!-- wp:column {"width":"70%"} -->
<div class="wp-block-column" style="flex-basis:70%"><!-- wp:paragraph {"align":"right","style":{"typography":{"fontSize":16}}} -->
<p class="has-text-align-right" style="font-size:16px">Fields marked with <strong class="has-accent-color has-text-color">*</strong> are required.</p>
<!-- /wp:paragraph --></div>
<!-- /wp:column --></div>
<!-- /wp:columns --></div>
<!-- /wp:group --></div>
<!-- /wp:wpzoom-forms/form -->'
	),
	array(
		'id'      => 'order-form',
		'name'    => 'Request a Quote',
		
		'icon'    => '<svg width="16" height="20" viewBox="0 0 16 20" fill="none" xmlns="http://www.w3.org/2000/svg">
		<path d="M15 5H12V4C12 2.93913 11.5786 1.92172 10.8284 1.17157C10.0783 0.421427 9.06087 0 8 0C6.93913 0 5.92172 0.421427 5.17157 1.17157C4.42143 1.92172 4 2.93913 4 4V5H1C0.734784 5 0.48043 5.10536 0.292893 5.29289C0.105357 5.48043 0 5.73478 0 6V17C0 17.7956 0.316071 18.5587 0.87868 19.1213C1.44129 19.6839 2.20435 20 3 20H13C13.7956 20 14.5587 19.6839 15.1213 19.1213C15.6839 18.5587 16 17.7956 16 17V6C16 5.73478 15.8946 5.48043 15.7071 5.29289C15.5196 5.10536 15.2652 5 15 5ZM6 4C6 3.46957 6.21071 2.96086 6.58579 2.58579C6.96086 2.21071 7.46957 2 8 2C8.53043 2 9.03914 2.21071 9.41421 2.58579C9.78929 2.96086 10 3.46957 10 4V5H6V4ZM14 17C14 17.2652 13.8946 17.5196 13.7071 17.7071C13.5196 17.8946 13.2652 18 13 18H3C2.73478 18 2.48043 17.8946 2.29289 17.7071C2.10536 17.5196 2 17.2652 2 17V7H4V8C4 8.26522 4.10536 8.51957 4.29289 8.70711C4.48043 8.89464 4.73478 9 5 9C5.26522 9 5.51957 8.89464 5.70711 8.70711C5.89464 8.51957 6 8.26522 6 8V7H10V8C10 8.26522 10.1054 8.51957 10.2929 8.70711C10.4804 8.89464 10.7348 9 11 9C11.2652 9 11.5196 8.89464 11.7071 8.70711C11.8946 8.51957 12 8.26522 12 8V7H14V17Z" />
		</svg>',

		'desc'    => 'A simple form to request a quote for a service.',
		'content' => '<!-- wp:wpzoom-forms/form -->
<div class="wp-block-wpzoom-forms-form"><!-- wp:group -->
<div class="wp-block-group"><!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"100%"} -->
<div class="wp-block-column" style="flex-basis:100%"><!-- wp:wpzoom-forms/text-name-field {"id":"input_name","name":"Your Name","label":"Your Name","className":"fullwidth"} -->
<label for="input_name"><label for="input_name">Your Name</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_name" id="input_name" placeholder="" required class="wp-block-wpzoom-forms-text-name-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-name-field -->

<!-- wp:wpzoom-forms/text-email-field {"id":"input_email","name":"Your Email","label":"Your Email","replyto":true,"className":"fullwidth"} -->
<label for="input_email"><label for="input_email">Your Email</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="email" name="input_email" id="input_email" placeholder="" required data-replyto="true" class="wp-block-wpzoom-forms-text-email-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-email-field -->

<!-- wp:wpzoom-forms/text-phone-field {"id":"input_9ff254c3","name":"Phone Number","label":"Phone Number"} -->
<label for="input_9ff254c3"><label for="input_9ff254c3">Phone Number</label></label><input type="tel" name="input_9ff254c3" id="input_9ff254c3" placeholder="" class="wp-block-wpzoom-forms-text-phone-field"/>
<!-- /wp:wpzoom-forms/text-phone-field -->

<!-- wp:wpzoom-forms/text-plain-field {"id":"input_984c7374","name":"Your Address","label":"Your Address"} -->
<label for="input_984c7374"><label for="input_984c7374">Your Address</label></label><input type="text" name="input_984c7374" id="input_984c7374" placeholder="" data-subject="false" class="wp-block-wpzoom-forms-text-plain-field"/>
<!-- /wp:wpzoom-forms/text-plain-field -->

<!-- wp:wpzoom-forms/multi-checkbox-field {"id":"input_b627c5c7","name":"Required service(s)","options":["General maintenance","Repairs","House keeping","Landscape gardening","Other"],"label":"Required service(s)"} -->
<label for="input_b627c5c7"><label for="input_b627c5c7">Required service(s)</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><ul class="wp-block-wpzoom-forms-multi-checkbox-field"><li><label><input type="checkbox" name="input_b627c5c7[]" id="input_b627c5c7" value="General maintenance" required/>General maintenance</label></li><li><label><input type="checkbox" name="input_b627c5c7[]" id="input_b627c5c7" value="Repairs" required/>Repairs</label></li><li><label><input type="checkbox" name="input_b627c5c7[]" id="input_b627c5c7" value="House keeping" required/>House keeping</label></li><li><label><input type="checkbox" name="input_b627c5c7[]" id="input_b627c5c7" value="Landscape gardening" required/>Landscape gardening</label></li><li><label><input type="checkbox" name="input_b627c5c7[]" id="input_b627c5c7" value="Other" required/>Other</label></li></ul>
<!-- /wp:wpzoom-forms/multi-checkbox-field -->

<!-- wp:spacer {"height":"34px"} -->
<div style="height:34px" aria-hidden="true" class="wp-block-spacer"></div>
<!-- /wp:spacer -->

<!-- wp:wpzoom-forms/textarea-field {"id":"input_message","name":"How can we help you?","label":"How can we help you?","className":"fullwidth"} -->
<label for="input_message"><label for="input_message">How can we help you?</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><textarea name="input_message" id="input_message" cols="55" rows="10" placeholder="" required class="wp-block-wpzoom-forms-textarea-field fullwidth"></textarea>
<!-- /wp:wpzoom-forms/textarea-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"30%"} -->
<div class="wp-block-column" style="flex-basis:30%"><!-- wp:wpzoom-forms/submit-field {"id":"input_submit"} -->
<input type="submit" id="input_submit" value="Submit" class="wp-block-wpzoom-forms-submit-field"/>
<!-- /wp:wpzoom-forms/submit-field --></div>
<!-- /wp:column -->

<!-- wp:column {"width":"70%"} -->
<div class="wp-block-column" style="flex-basis:70%"><!-- wp:paragraph {"align":"right","style":{"typography":{"fontSize":16}}} -->
<p class="has-text-align-right" style="font-size:16px">Fields marked with <strong class="has-accent-color has-text-color">*</strong> are required.</p>
<!-- /wp:paragraph --></div>
<!-- /wp:column --></div>
<!-- /wp:columns --></div>
<!-- /wp:group --></div>
<!-- /wp:wpzoom-forms/form -->'
	),
	array(
		'id'      => 'feedback-form',
		'name'    => 'Feedback Form',
		
		'icon'    => '<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
		<path d="M14.36 14.23C13.6915 14.769 12.8587 15.0629 12 15.0629C11.1413 15.0629 10.3085 14.769 9.64 14.23C9.43579 14.0603 9.17251 13.9786 8.90808 14.003C8.64365 14.0274 8.39974 14.1558 8.23 14.36C8.06027 14.5642 7.9786 14.8275 8.00298 15.0919C8.02736 15.3563 8.15579 15.6003 8.36 15.77C9.38134 16.6226 10.6696 17.0896 12 17.0896C13.3304 17.0896 14.6187 16.6226 15.64 15.77C15.8442 15.6003 15.9726 15.3563 15.997 15.0919C16.0214 14.8275 15.9397 14.5642 15.77 14.36C15.686 14.2589 15.5828 14.1753 15.4665 14.1141C15.3501 14.0528 15.2229 14.0151 15.0919 14.003C14.8275 13.9786 14.5642 14.0603 14.36 14.23ZM9 11C9.19779 11 9.39113 10.9414 9.55557 10.8315C9.72002 10.7216 9.8482 10.5654 9.92388 10.3827C9.99957 10.2 10.0194 9.99889 9.98079 9.80491C9.9422 9.61093 9.84696 9.43275 9.70711 9.29289C9.56726 9.15304 9.38908 9.0578 9.19509 9.01921C9.00111 8.98063 8.80005 9.00043 8.61732 9.07612C8.43459 9.15181 8.27842 9.27998 8.16853 9.44443C8.05865 9.60888 8 9.80222 8 10C8 10.2652 8.10536 10.5196 8.2929 10.7071C8.48043 10.8946 8.73479 11 9 11ZM15 9C14.8022 9 14.6089 9.05865 14.4444 9.16853C14.28 9.27841 14.1518 9.43459 14.0761 9.61732C14.0004 9.80004 13.9806 10.0011 14.0192 10.1951C14.0578 10.3891 14.153 10.5673 14.2929 10.7071C14.4328 10.847 14.6109 10.9422 14.8049 10.9808C14.9989 11.0194 15.2 10.9996 15.3827 10.9239C15.5654 10.8482 15.7216 10.72 15.8315 10.5556C15.9414 10.3911 16 10.1978 16 10C16 9.73478 15.8946 9.48043 15.7071 9.29289C15.5196 9.10536 15.2652 9 15 9ZM12 2C10.0222 2 8.08879 2.58649 6.4443 3.6853C4.79981 4.78412 3.51809 6.3459 2.76121 8.17317C2.00433 10.0004 1.8063 12.0111 2.19215 13.9509C2.578 15.8907 3.53041 17.6725 4.92894 19.0711C6.32746 20.4696 8.10929 21.422 10.0491 21.8079C11.9889 22.1937 13.9996 21.9957 15.8268 21.2388C17.6541 20.4819 19.2159 19.2002 20.3147 17.5557C21.4135 15.9112 22 13.9778 22 12C22 10.6868 21.7413 9.38642 21.2388 8.17317C20.7363 6.95991 19.9997 5.85752 19.0711 4.92893C18.1425 4.00035 17.0401 3.26375 15.8268 2.7612C14.6136 2.25866 13.3132 2 12 2ZM12 20C10.4178 20 8.87104 19.5308 7.55544 18.6518C6.23985 17.7727 5.21447 16.5233 4.60897 15.0615C4.00347 13.5997 3.84504 11.9911 4.15372 10.4393C4.4624 8.88743 5.22433 7.46197 6.34315 6.34315C7.46197 5.22433 8.88743 4.4624 10.4393 4.15372C11.9911 3.84504 13.5997 4.00346 15.0615 4.60896C16.5233 5.21447 17.7727 6.23984 18.6518 7.55544C19.5308 8.87103 20 10.4177 20 12C20 14.1217 19.1572 16.1566 17.6569 17.6569C16.1566 19.1571 14.1217 20 12 20Z" />
		</svg>',

		'desc'    => 'A simple form to collect feedback from your users.',
		'content' => '<!-- wp:wpzoom-forms/form -->
<div class="wp-block-wpzoom-forms-form"><!-- wp:group -->
<div class="wp-block-group"><!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"100%"} -->
<div class="wp-block-column" style="flex-basis:100%"><!-- wp:wpzoom-forms/text-name-field {"id":"input_name","name":"Name","label":"Name","className":"fullwidth"} -->
<label for="input_name"><label for="input_name">Name</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_name" id="input_name" placeholder="" required class="wp-block-wpzoom-forms-text-name-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-name-field -->

<!-- wp:wpzoom-forms/text-email-field {"id":"input_email","name":"Email","label":"Email","replyto":true,"className":"fullwidth"} -->
<label for="input_email"><label for="input_email">Email</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="email" name="input_email" id="input_email" placeholder="" required data-replyto="true" class="wp-block-wpzoom-forms-text-email-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-email-field -->

<!-- wp:wpzoom-forms/select-field {"id":"input_8e4aa5c6","name":"Department","options":["Sales","Customer Support","Marketing","Development","Other"],"label":"Which department do you have a suggestion for?"} -->
<label for="input_8e4aa5c6"><label for="input_8e4aa5c6">Which department do you have a suggestion for?</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><select name="input_8e4aa5c6" id="input_8e4aa5c6" required defaultvalue="Item #1" class="wp-block-wpzoom-forms-select-field"><option value="Sales">Sales</option><option value="Customer Support">Customer Support</option><option value="Marketing">Marketing</option><option value="Development">Development</option><option value="Other">Other</option></select>
<!-- /wp:wpzoom-forms/select-field -->

<!-- wp:wpzoom-forms/text-plain-field {"id":"input_subject","name":"Subject","label":"Subject","subject":true,"className":"fullwidth"} -->
<label for="input_subject"><label for="input_subject">Subject</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_subject" id="input_subject" placeholder="" required data-subject="true" class="wp-block-wpzoom-forms-text-plain-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-plain-field -->

<!-- wp:wpzoom-forms/textarea-field {"id":"input_message","name":"Message","label":"Message","className":"fullwidth"} -->
<label for="input_message"><label for="input_message">Message</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><textarea name="input_message" id="input_message" cols="55" rows="10" placeholder="" required class="wp-block-wpzoom-forms-textarea-field fullwidth"></textarea>
<!-- /wp:wpzoom-forms/textarea-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"30%"} -->
<div class="wp-block-column" style="flex-basis:30%"><!-- wp:wpzoom-forms/submit-field {"id":"input_submit"} -->
<input type="submit" id="input_submit" value="Submit" class="wp-block-wpzoom-forms-submit-field"/>
<!-- /wp:wpzoom-forms/submit-field --></div>
<!-- /wp:column -->

<!-- wp:column {"width":"70%"} -->
<div class="wp-block-column" style="flex-basis:70%"><!-- wp:paragraph {"align":"right","style":{"typography":{"fontSize":16}}} -->
<p class="has-text-align-right" style="font-size:16px">Fields marked with <strong class="has-accent-color has-text-color">*</strong> are required.</p>
<!-- /wp:paragraph --></div>
<!-- /wp:column --></div>
<!-- /wp:columns --></div>
<!-- /wp:group --></div>
<!-- /wp:wpzoom-forms/form -->'
	),
	array(
		'id'      => 'appointment-form',
		'name'    => 'Appointment and Reservation Form (Salon)',
		
		'icon'    =>  '<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M12 14C12.1978 14 12.3911 13.9414 12.5556 13.8315C12.72 13.7216 12.8482 13.5654 12.9239 13.3827C12.9996 13.2 13.0194 12.9989 12.9808 12.8049C12.9422 12.6109 12.847 12.4327 12.7071 12.2929C12.5673 12.153 12.3891 12.0578 12.1951 12.0192C12.0011 11.9806 11.8 12.0004 11.6173 12.0761C11.4346 12.1518 11.2784 12.28 11.1685 12.4444C11.0586 12.6089 11 12.8022 11 13C11 13.2652 11.1054 13.5196 11.2929 13.7071C11.4804 13.8946 11.7348 14 12 14ZM17 14C17.1978 14 17.3911 13.9414 17.5556 13.8315C17.72 13.7216 17.8482 13.5654 17.9239 13.3827C17.9996 13.2 18.0194 12.9989 17.9808 12.8049C17.9422 12.6109 17.847 12.4327 17.7071 12.2929C17.5673 12.153 17.3891 12.0578 17.1951 12.0192C17.0011 11.9806 16.8 12.0004 16.6173 12.0761C16.4346 12.1518 16.2784 12.28 16.1685 12.4444C16.0586 12.6089 16 12.8022 16 13C16 13.2652 16.1054 13.5196 16.2929 13.7071C16.4804 13.8946 16.7348 14 17 14ZM12 18C12.1978 18 12.3911 17.9414 12.5556 17.8315C12.72 17.7216 12.8482 17.5654 12.9239 17.3827C12.9996 17.2 13.0194 16.9989 12.9808 16.8049C12.9422 16.6109 12.847 16.4327 12.7071 16.2929C12.5673 16.153 12.3891 16.0578 12.1951 16.0192C12.0011 15.9806 11.8 16.0004 11.6173 16.0761C11.4346 16.1518 11.2784 16.28 11.1685 16.4444C11.0586 16.6089 11 16.8022 11 17C11 17.2652 11.1054 17.5196 11.2929 17.7071C11.4804 17.8946 11.7348 18 12 18ZM17 18C17.1978 18 17.3911 17.9414 17.5556 17.8315C17.72 17.7216 17.8482 17.5654 17.9239 17.3827C17.9996 17.2 18.0194 16.9989 17.9808 16.8049C17.9422 16.6109 17.847 16.4327 17.7071 16.2929C17.5673 16.153 17.3891 16.0578 17.1951 16.0192C17.0011 15.9806 16.8 16.0004 16.6173 16.0761C16.4346 16.1518 16.2784 16.28 16.1685 16.4444C16.0586 16.6089 16 16.8022 16 17C16 17.2652 16.1054 17.5196 16.2929 17.7071C16.4804 17.8946 16.7348 18 17 18ZM7 14C7.19778 14 7.39112 13.9414 7.55557 13.8315C7.72002 13.7216 7.84819 13.5654 7.92388 13.3827C7.99957 13.2 8.01937 12.9989 7.98079 12.8049C7.9422 12.6109 7.84696 12.4327 7.70711 12.2929C7.56725 12.153 7.38907 12.0578 7.19509 12.0192C7.00111 11.9806 6.80004 12.0004 6.61732 12.0761C6.43459 12.1518 6.27841 12.28 6.16853 12.4444C6.05865 12.6089 6 12.8022 6 13C6 13.2652 6.10536 13.5196 6.29289 13.7071C6.48043 13.8946 6.73478 14 7 14ZM19 4H18V3C18 2.73478 17.8946 2.48043 17.7071 2.29289C17.5196 2.10536 17.2652 2 17 2C16.7348 2 16.4804 2.10536 16.2929 2.29289C16.1054 2.48043 16 2.73478 16 3V4H8V3C8 2.73478 7.89464 2.48043 7.70711 2.29289C7.51957 2.10536 7.26522 2 7 2C6.73478 2 6.48043 2.10536 6.29289 2.29289C6.10536 2.48043 6 2.73478 6 3V4H5C4.20435 4 3.44129 4.31607 2.87868 4.87868C2.31607 5.44129 2 6.20435 2 7V19C2 19.7956 2.31607 20.5587 2.87868 21.1213C3.44129 21.6839 4.20435 22 5 22H19C19.7956 22 20.5587 21.6839 21.1213 21.1213C21.6839 20.5587 22 19.7956 22 19V7C22 6.20435 21.6839 5.44129 21.1213 4.87868C20.5587 4.31607 19.7956 4 19 4ZM20 19C20 19.2652 19.8946 19.5196 19.7071 19.7071C19.5196 19.8946 19.2652 20 19 20H5C4.73478 20 4.48043 19.8946 4.29289 19.7071C4.10536 19.5196 4 19.2652 4 19V10H20V19ZM20 8H4V7C4 6.73478 4.10536 6.48043 4.29289 6.29289C4.48043 6.10536 4.73478 6 5 6H19C19.2652 6 19.5196 6.10536 19.7071 6.29289C19.8946 6.48043 20 6.73478 20 7V8ZM7 18C7.19778 18 7.39112 17.9414 7.55557 17.8315C7.72002 17.7216 7.84819 17.5654 7.92388 17.3827C7.99957 17.2 8.01937 16.9989 7.98079 16.8049C7.9422 16.6109 7.84696 16.4327 7.70711 16.2929C7.56725 16.153 7.38907 16.0578 7.19509 16.0192C7.00111 15.9806 6.80004 16.0004 6.61732 16.0761C6.43459 16.1518 6.27841 16.28 6.16853 16.4444C6.05865 16.6089 6 16.8022 6 17C6 17.2652 6.10536 17.5196 6.29289 17.7071C6.48043 17.8946 6.73478 18 7 18Z" />
</svg>',

		'desc'    => 'With this form, you can let customers request an appointment, allowing you to get back to them for confirmation depending on availability at your hair salon.',
		'content' => '<!-- wp:wpzoom-forms/form -->
<div class="wp-block-wpzoom-forms-form"><!-- wp:group -->
<div class="wp-block-group"><!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"100%"} -->
<div class="wp-block-column" style="flex-basis:100%"><!-- wp:wpzoom-forms/text-name-field {"id":"input_name","name":"Name","label":"Your Name","className":"fullwidth"} -->
<label for="input_name"><label for="input_name">Your Name</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="text" name="input_name" id="input_name" placeholder="" required class="wp-block-wpzoom-forms-text-name-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-name-field -->

<!-- wp:wpzoom-forms/text-email-field {"id":"input_email","name":"Email","label":"Email","replyto":true,"className":"fullwidth"} -->
<label for="input_email"><label for="input_email">Email</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="email" name="input_email" id="input_email" placeholder="" required data-replyto="true" class="wp-block-wpzoom-forms-text-email-field fullwidth"/>
<!-- /wp:wpzoom-forms/text-email-field -->

<!-- wp:wpzoom-forms/text-phone-field {"id":"input_48cf9a05","name":"Phone","label":"Your Phone"} -->
<label for="input_48cf9a05"><label for="input_48cf9a05">Your Phone</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input type="tel" name="input_48cf9a05" id="input_48cf9a05" placeholder="" required class="wp-block-wpzoom-forms-text-phone-field"/>
<!-- /wp:wpzoom-forms/text-phone-field -->

<!-- wp:wpzoom-forms/multi-checkbox-field {"id":"input_02ad61ed","name":"Services","options":["Hair Consult","Blow Dry Style","Balayage","Highlights","Hair Mask","Men\'s Cut"],"label":"Services Requested"} -->
<label for="input_02ad61ed"><label for="input_02ad61ed">Services Requested</label></label><ul class="wp-block-wpzoom-forms-multi-checkbox-field"><li><label><input type="checkbox" name="input_02ad61ed[]" id="input_02ad61ed" value="Hair Consult"/>Hair Consult</label></li><li><label><input type="checkbox" name="input_02ad61ed[]" id="input_02ad61ed" value="Blow Dry Style"/>Blow Dry Style</label></li><li><label><input type="checkbox" name="input_02ad61ed[]" id="input_02ad61ed" value="Balayage"/>Balayage</label></li><li><label><input type="checkbox" name="input_02ad61ed[]" id="input_02ad61ed" value="Highlights"/>Highlights</label></li><li><label><input type="checkbox" name="input_02ad61ed[]" id="input_02ad61ed" value="Hair Mask"/>Hair Mask</label></li><li><label><input type="checkbox" name="input_02ad61ed[]" id="input_02ad61ed" value="Men\'s Cut"/>Men\'s Cut</label></li></ul>
<!-- /wp:wpzoom-forms/multi-checkbox-field -->

<!-- wp:wpzoom-forms/datepicker-field {"id":"input_de9be238","name":"Date","format":"d/m/Y","label":"Date"} -->
<label for="input_de9be238"><label for="input_de9be238">Date</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><input data-datepicker="true" data-date-format="d/m/Y" data-mode="single" type="text" name="input_de9be238" id="input_de9be238" placeholder="d/m/Y" required class="wp-block-wpzoom-forms-datepicker-field"/>
<!-- /wp:wpzoom-forms/datepicker-field -->

<!-- wp:wpzoom-forms/select-field {"id":"input_59b29aed","name":"Start Time","options":["09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30"],"label":"Start Time"} -->
<label for="input_59b29aed"><label for="input_59b29aed">Start Time</label><sup class="wp-block-wpzoom-forms-required">*</sup></label><select name="input_59b29aed" id="input_59b29aed" required defaultvalue="Item #1" class="wp-block-wpzoom-forms-select-field"><option value="09:00">09:00</option><option value="09:30">09:30</option><option value="10:00">10:00</option><option value="10:30">10:30</option><option value="11:00">11:00</option><option value="11:30">11:30</option><option value="12:00">12:00</option><option value="12:30">12:30</option><option value="13:00">13:00</option><option value="13:30">13:30</option><option value="14:00">14:00</option><option value="14:30">14:30</option><option value="15:00">15:00</option><option value="15:30">15:30</option><option value="16:00">16:00</option><option value="16:30">16:30</option><option value="17:00">17:00</option><option value="17:30">17:30</option></select>
<!-- /wp:wpzoom-forms/select-field -->

<!-- wp:wpzoom-forms/textarea-field {"id":"input_message","name":"Any notes?","label":"Any notes?","className":"fullwidth"} -->
<label for="input_message"><label for="input_message">Any notes?</label></label><textarea name="input_message" id="input_message" cols="55" rows="3" placeholder="" class="wp-block-wpzoom-forms-textarea-field fullwidth"></textarea>
<!-- /wp:wpzoom-forms/textarea-field --></div>
<!-- /wp:column --></div>
<!-- /wp:columns -->

<!-- wp:columns -->
<div class="wp-block-columns"><!-- wp:column {"width":"30%"} -->
<div class="wp-block-column" style="flex-basis:30%"><!-- wp:wpzoom-forms/submit-field {"id":"input_submit"} -->
<input type="submit" id="input_submit" value="Submit" class="wp-block-wpzoom-forms-submit-field"/>
<!-- /wp:wpzoom-forms/submit-field --></div>
<!-- /wp:column -->

<!-- wp:column {"width":"70%"} -->
<div class="wp-block-column" style="flex-basis:70%"><!-- wp:paragraph {"align":"right","style":{"typography":{"fontSize":16}}} -->
<p class="has-text-align-right" style="font-size:16px">Fields marked with <strong class="has-accent-color has-text-color">*</strong> are required.</p>
<!-- /wp:paragraph --></div>
<!-- /wp:column --></div>
<!-- /wp:columns --></div>
<!-- /wp:group --></div>
<!-- /wp:wpzoom-forms/form -->'
	)
);

return $templates;
