<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * Localized language
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'impulsewordpresssae_wordpress' );

/** Database username */
define( 'DB_USER', '376612_wordpress' );

/** Database password */
define( 'DB_PASSWORD', 'AOylxRup18' );

/** Database hostname */
define( 'DB_HOST', 'mysql-impulsewordpresssae.alwaysdata.net' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',          '2X,??z4?$?/Li56X~gskp5=RxHdpSN,Tr;Mm-+#,=GbH?~fCTyLTNNj$GwyYGRtU' );
define( 'SECURE_AUTH_KEY',   '9u0rQvF}{^mf=;to~FlaDm4V(g(saL2R2GZ[hp?!a%lkdGjg~g/z$u:(<JM8ocaX' );
define( 'LOGGED_IN_KEY',     'T%/GLWjv.TdD.xB:OK0?=;/]yj.Fn7UR~0P-Ao^x.q+}llw2q&jtb#InEKF3fI]m' );
define( 'NONCE_KEY',         'G<L4U{4W@|3.=e_+A6P&#U+9/:;G[y_0QV5Q;rpfdd:tIu]j2q3[djWPq;$=Q#B}' );
define( 'AUTH_SALT',         'W#jQ*evDL![T(E{0ie)G~jJXs]R:&b?+<cMyZ#_#4Z<AI(Z_Qy`J%&MAKq+LjEo~' );
define( 'SECURE_AUTH_SALT',  '<balyj>lp3xOO[k!ygK9J7zjqM8HzM4vqU4]sq :ZBv[:+UE yxX@#^3~KACYAgE' );
define( 'LOGGED_IN_SALT',    'Nx|klWgnuef x6m;GTY29mw^+-s^F6l;O3eJN}-tl+N75KIe[1`t;6}HeN@YVKm1' );
define( 'NONCE_SALT',        '-NN-M=?8RgZ&E/&Vl{P[O?1t@KfJ%qq~Wx`,=T_v(9U3Kv_khM3Wg!U|zTTrduFk' );
define( 'WP_CACHE_KEY_SALT', '*k8S.GXe=CJ{Z9GT.DxT,VF+5NZlv7#/ueBz>~g|AU*gG=>cp8%lqk=sBfBIXxep' );


/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';


/* Add any custom values between this line and the "stop editing" line. */



/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
if ( ! defined( 'WP_DEBUG' ) ) {
	define( 'WP_DEBUG', false );
}

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
