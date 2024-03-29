AC_INIT([icedtea6],[1.13.10],[distro-pkg-dev@openjdk.java.net])
AC_CANONICAL_HOST
AC_CANONICAL_TARGET
AM_INIT_AUTOMAKE([1.9 tar-pax foreign])
AC_CONFIG_FILES([Makefile fsg.sh])

AM_MAINTAINER_MODE

# Older automake doesn't generate these correctly
abs_top_builddir=`pwd -P`
AC_SUBST(abs_top_builddir)
abs_top_srcdir=`dirname $0`
cd $abs_top_srcdir
abs_top_srcdir=`pwd`
cd $abs_top_builddir
AC_SUBST(abs_top_srcdir)

AC_PREFIX_DEFAULT([bootstrap])

AC_PROG_CC
if test "x$CC" = x; then
   AC_MSG_ERROR([No C compiler found.])
fi

AC_PROG_CXX
if test "x$CXX" = x; then
   AC_MSG_ERROR([No C++ compiler found.])
fi

IT_FIND_TOOL([MAKE], [make])
IT_FIND_TOOL([GZIP], [gzip])
IT_FIND_TOOL([ANT], [ant])
IT_FIND_TOOL([FIND], [find])
IT_FIND_TOOL([PATCH], [patch])
IT_FIND_TOOL([TAR], [tar])
IT_FIND_TOOL([CHMOD], [chmod])
IT_FIND_TOOL([SHA256SUM], [sha256sum])
IT_FIND_TOOL([WGET], [wget])
IT_FIND_TOOL([ZIP], [zip])
IT_FIND_TOOL([UNZIP], [unzip])
IT_FIND_TOOL([CPIO], [cpio])
IT_FIND_TOOL([FILE], [file])
AC_CHECK_TOOLS([FASTJAR], [fastjar jar])
if test "x$FASTJAR" = x; then
	AC_MSG_ERROR([Can't find fastjar or jar])
fi
AC_SUBST([FASTJAR])
AC_CHECK_TOOL([LDD], [ldd])
dnl OpenJDK's README-builds.html lists gawk as a build dependency so we
dnl check for it explicitly rather than using AC_PROG_AWK.
IT_FIND_TOOL([GAWK], [gawk])
IT_CHECK_FOR_MERCURIAL
IT_OBTAIN_HG_REVISIONS
AC_PATH_TOOL([LSB_RELEASE],[lsb_release])
IT_WITH_GCJ
IT_WITH_HOTSPOT_BUILD
AC_PATH_TOOL([LINUX32],[linux32])
IT_CHECK_GCC_VERSION
IT_CHECK_NUMBER_OF_PARALLEL_JOBS
IT_CP_SUPPORTS_REFLINK
IT_CAN_HARDLINK_TO_SOURCE_TREE
IT_LOCATE_NSS
IT_DISABLE_TESTS
IT_DISABLE_HOTSPOT_TESTS
IT_DISABLE_LANGTOOLS_TESTS
IT_DISABLE_JDK_TESTS

IT_WITH_FONTS_DIR
AC_CONFIG_FILES([linux.fontconfig.Gentoo.properties])

dnl PR42003 - Missing javax.swing.plaf.basic.BasicDirectoryModel methods cause OpenJDK build failure 
IT_CHECK_FOR_METHOD([JAVAX_SWING_PLAF_BASIC_BASICDIRECTORYMODEL_ADDPROPERTYCHANGELISTENER],
  [javax.swing.plaf.basic.BasicDirectoryModel.addPropertyChangeListener(PropertyChangeListener)],
  [javax.swing.plaf.basic.BasicDirectoryModel],
  ["addPropertyChangeListener",java.beans.PropertyChangeListener.class],
  [javax.swing.plaf.basic.BasicDirectoryModel model = new javax.swing.plaf.basic.BasicDirectoryModel(new javax.swing.JFileChooser()); model.addPropertyChangeListener(model)]
)

dnl Workaround issue with old CORBA classes in old OpenJDK builds
IT_CHECK_FOR_CLASS([ORBUTILSYSTEMEXCEPTION],[com.sun.corba.se.impl.logging.ORBUtilSystemException])
IT_CHECK_FOR_METHOD([ORBUTILSYSTEMEXCEPTION_IOEXCEPTIONONCLOSE_THROWABLE],
  [com.sun.corba.se.impl.logging.ORBUtilSystemException.ioExceptionOnClose(Throwable)],
  [com.sun.corba.se.impl.logging.ORBUtilSystemException],
  ["ioExceptionOnClose",java.lang.Throwable.class],
  [new com.sun.corba.se.impl.logging.ORBUtilSystemException(null).ioExceptionOnClose(new InternalError())]
)

dnl PR43389 - javax.management.StandardMBean
IT_CHECK_FOR_CONSTRUCTOR([JAVAX_MANAGEMENT_STANDARDMBEAN_MXBEAN_TWO_ARG],[javax.management.StandardMBean],[Class.class,Boolean.TYPE],[Object.class, true])
IT_CHECK_FOR_CONSTRUCTOR([JAVAX_MANAGEMENT_STANDARDMBEAN_MXBEAN_THREE_ARG],[javax.management.StandardMBean],[Object.class,Class.class,Boolean.TYPE],[new Object(), Object.class, true])

dnl PR54274 - sun.security.other requires missing X509CRLSelector methods
IT_CHECK_FOR_METHOD([JAVA_SECURITY_CERT_X509CRLSELECTOR_ADDISSUER],
  [java.security.cert.X509CRLSelector.addIssuer(X500Principal)],
  [java.security.cert.X509CRLSelector],
  ["addIssuer",javax.security.auth.x500.X500Principal.class],
  [new java.security.cert.X509CRLSelector().addIssuer(null)]
)
IT_CHECK_FOR_METHOD([JAVA_SECURITY_CERT_X509CERTSELECTOR_SETSUBJECT],
  [java.security.cert.X509CertSelector.setSubject(X500Principal)],
  [java.security.cert.X509CertSelector],
  ["setSubject",javax.security.auth.x500.X500Principal.class],
  [new java.security.cert.X509CertSelector().setSubject(new javax.security.auth.x500.X500Principal(""))]
)
IT_CHECK_FOR_METHOD([JAVA_SECURITY_CERT_X509CERTSELECTOR_GETSUBJECT],
  [java.security.cert.X509CertSelector.getSubject()],
  [java.security.cert.X509CertSelector],
  ["getSubject"],
  [new java.security.cert.X509CertSelector().getSubject()]
)
IT_CHECK_FOR_METHOD([JAVA_SECURITY_CERT_X509CERTSELECTOR_SETISSUER],
  [java.security.cert.X509CertSelector.setIssuer(X500Principal)],
  [java.security.cert.X509CertSelector],
  ["setIssuer",javax.security.auth.x500.X500Principal.class],
  [new java.security.cert.X509CertSelector().setIssuer(new javax.security.auth.x500.X500Principal(""))]
)
IT_CHECK_FOR_METHOD([JAVA_SECURITY_CERT_X509CRLSELECTOR_SETISSUERS],
  [java.security.cert.X509CRLSelector.setIssuers(Collection<X500Principal>)],
  [java.security.cert.X509CRLSelector],
  ["setIssuers",java.util.Collection.class],
  [new java.security.cert.X509CRLSelector().setIssuers(new java.util.ArrayList<javax.security.auth.x500.X500Principal>())]
)
IT_CHECK_FOR_METHOD([JAVAX_SECURITY_AUTH_KERBEROS_KERBEROSTICKET_GETSESSIONKEYTYPE],
  [javax.security.auth.kerberos.KerberosTicket.getSessionKeyType()],
  [javax.security.auth.kerberos.KerberosTicket],
  ["getSessionKeyType"],
  [new javax.security.auth.kerberos.KerberosTicket(null,null,null,null,0,null,null,null,null,null,null).getSessionKeyType()]
)

dnl PR54275 - sun.security.sasl requires missing Sasl.CREDENTIALS field
IT_CHECK_FOR_STATIC_FIELD([JAVAX_SECURITY_SASL_SASL_CREDENTIALS],[CREDENTIALS],[javax.security.sasl.Sasl])

dnl PR56553 - SSLParameters support missing
IT_CHECK_FOR_METHOD([JAVAX_NET_SSL_SSLCONTEXT_GETDEFAULTSSLPARAMETERS],
  [javax.net.ssl.SSLContext.getDefaultSSLParameters],
  [javax.net.ssl.SSLContext],
  ["getDefaultSSLParameters"],
  [try { javax.net.ssl.SSLContext.getDefault().getDefaultSSLParameters(); } catch (Exception e) {}]
)
IT_CHECK_FOR_METHOD([JAVAX_NET_SSL_SSLENGINE_SETSSLPARAMETERS],
  [javax.net.ssl.SSLEngine.setSSLParameters],
  [javax.net.ssl.SSLEngine],
  ["setSSLParameters", javax.net.ssl.SSLParameters.class],
  [try { javax.net.ssl.SSLContext.getDefault().createSSLEngine().setSSLParameters(new javax.net.ssl.SSLParameters()); }
   catch (Exception e) {}]
)

dnl PR57030 - Add missing TrustAnchor methods
IT_CHECK_FOR_CONSTRUCTOR([JAVA_SECURITY_CERT_TRUSTANCHOR_X500_CONS],
  [java.security.cert.TrustAnchor],
  [javax.security.auth.x500.X500Principal.class, java.security.PublicKey.class, byte@<:@@:>@.class],
  [new javax.security.auth.x500.X500Principal("bob"),null,null]
)
IT_CHECK_FOR_METHOD([JAVA_SECURITY_CERT_TRUSTANCHOR_GETCA],
  [java.security.cert.TrustAnchor.getCA],
  [java.security.cert.TrustAnchor],
  ["getCA"],
  [new java.security.cert.TrustAnchor("", null, null).getCA()]
)

dnl PR57031 - Add missing getParameters() method in KeyStoreBuilderParameters
IT_CHECK_FOR_METHOD([JAVAX_NET_SSL_KEYSTOREBUILDERPARAMETERS_GETPARAMETERS],
  [javax.net.ssl.KeyStoreBuilderParameters.getParameters],
  [javax.net.ssl.KeyStoreBuilderParameters],
  ["getParameters"],
  [new javax.net.ssl.KeyStoreBuilderParameters(new java.util.ArrayList<java.security.KeyStore.Builder>()).getParameters()]
)

dnl PR57420 - java.sql.SQLException
IT_CHECK_FOR_CONSTRUCTOR([JAVA_SQL_EXCEPTION_THROWABLE],[java.sql.SQLException],[Throwable.class],[new Throwable()])
IT_CHECK_FOR_CONSTRUCTOR([JAVA_SQL_EXCEPTION_REASON_THROWABLE],[java.sql.SQLException],[String.class,Throwable.class],["Something went wrong",new Throwable()])
IT_CHECK_FOR_CONSTRUCTOR([JAVA_SQL_EXCEPTION_REASON_STATE_THROWABLE],[java.sql.SQLException],[String.class,String.class,Throwable.class],["Something went wrong","",new Throwable()])
IT_CHECK_FOR_CONSTRUCTOR([JAVA_SQL_EXCEPTION_REASON_STATE_CODE_THROWABLE],[java.sql.SQLException],[String.class,String.class,Integer.TYPE,Throwable.class],["Something went wrong","",666,new Throwable()])

dnl Check whether the JDK can parse the dates used in the currency file
IT_PR64174_CHECK

# Use xvfb-run if found to run gui tests (check-jdk).
AC_CHECK_PROG(XVFB_RUN_CMD, xvfb-run, [xvfb-run -a -e xvfb-errors], [])
AC_SUBST(XVFB_RUN_CMD)

AC_MSG_CHECKING([for an Ant home directory])
AC_ARG_WITH([ant-home],
            [AS_HELP_STRING([--with-ant-home],
                            [Ant home directory (default is /usr/share/ant)])],
            [
              if test "x${withval}" = x
              then
                SYSTEM_ANT_DIR=/usr/share/ant
              else
                SYSTEM_ANT_DIR=${withval}
              fi
            ],
            [
              SYSTEM_ANT_DIR=/usr/share/ant
            ])
AC_SUBST(SYSTEM_ANT_DIR)
AC_MSG_RESULT(${SYSTEM_ANT_DIR})

AC_MSG_CHECKING([whether to include PulseAudio support])
AC_ARG_ENABLE([pulse-java],
              [AS_HELP_STRING([--enable-pulse-java],
                              [Enable pulse-java - an audio mixer spi that uses PulseAudio])],
              [enable_pulse_java="${enableval}"], [enable_pulse_java="no"])
AM_CONDITIONAL(ENABLE_PULSE_JAVA, test "x${enable_pulse_java}" = "xyes")
AC_MSG_RESULT(${enable_pulse_java})

AC_MSG_CHECKING([whether to build documentation])
AC_ARG_ENABLE([docs],
	      [AS_HELP_STRING([--disable-docs],
	      		      [Disable generation of documentation])],
	      [ENABLE_DOCS="${enableval}"], [ENABLE_DOCS='yes'])
AM_CONDITIONAL([ENABLE_DOCS], [test x$ENABLE_DOCS = xyes])
AC_MSG_RESULT(${ENABLE_DOCS})

# OpenJDK experimental cross-compilation support which is not yet complete
AC_MSG_CHECKING([whether to enable experimental OpenJDK cross-compilation support])
AC_ARG_ENABLE([openjdk-cross-compilation],
	      [AS_HELP_STRING([--enable-openjdk-cross-compilation],
	      		      [Enable experimental OpenJDK cross-compilation support])],
	      [ENABLE_CROSS_COMPILATION="${enableval}"], [ENABLE_CROSS_COMPILATION="${cross_compiling}"])
AM_CONDITIONAL([ENABLE_CROSS_COMPILATION], [test x$ENABLE_CROSS_COMPILATION = xyes])
AC_MSG_RESULT(${ENABLE_CROSS_COMPILATION})

AC_MSG_CHECKING([whether to include the XRender pipeline])
AC_ARG_ENABLE([xrender],
	      [AS_HELP_STRING([--disable-xrender],
	      		      [Disable inclusion of xrender pipeline])],
	      [ENABLE_XRENDER="${enableval}"], [ENABLE_XRENDER='yes'])
AM_CONDITIONAL([ENABLE_XRENDER], [test x$ENABLE_XRENDER = xyes])
AC_MSG_RESULT(${ENABLE_XRENDER})

AC_MSG_CHECKING([whether to include SystemTap tracing support])
AC_ARG_ENABLE([systemtap],
	      [AS_HELP_STRING([--enable-systemtap],
	      		      [Enable inclusion of SystemTap trace support])],
	      [ENABLE_SYSTEMTAP="${enableval}"], [ENABLE_SYSTEMTAP='no'])
AM_CONDITIONAL([ENABLE_SYSTEMTAP], [test x$ENABLE_SYSTEMTAP = xyes])
AC_MSG_RESULT(${ENABLE_SYSTEMTAP})

AC_MSG_CHECKING([whether to include the NSS-based security provider])
AC_ARG_ENABLE([nss],
	      [AS_HELP_STRING([--enable-nss],
	      		      [Enable inclusion of NSS security provider])],
	      [ENABLE_NSS="${enableval}"], [ENABLE_NSS='no'])
AM_CONDITIONAL([ENABLE_NSS], [test x$ENABLE_NSS = xyes])
if test "x${ENABLE_NSS}" = "xyes"
then
  AC_MSG_RESULT([enabled by default (edit java.security to disable)])
else
  AC_MSG_RESULT([disabled by default (edit java.security to enable)])
fi

IT_GET_PKGVERSION
IT_GET_LSB_DATA

IT_SET_ARCH_SETTINGS
IT_SET_OS_DIRS
OPENJDK_BUILD_DIR=openjdk.build
AC_SUBST(OPENJDK_BUILD_DIR)

IT_CHECK_IF_DOWNLOADING
IT_FIND_JAVA
IT_FIND_COMPILER
IT_FIND_JAVAH
IT_FIND_JAR
IT_FIND_RMIC
IT_FIND_NATIVE2ASCII
if test "x$enable_bootstrap" = "xyes"; then
  IT_FIND_ECJ_JAR
  IT_FIND_TOOL([XSLTPROC], [xsltproc])
fi
IT_USING_CACAO
AC_CONFIG_FILES([javac], [chmod +x javac])
AC_CONFIG_FILES([javap], [chmod +x javap])

IT_USE_BOOTSTRAP_TOOLS
IT_CHECK_FOR_XBOOTCLASSPATH

IT_FIND_RHINO_JAR
IT_WITH_OPENJDK_SRC_ZIP
IT_WITH_HOTSPOT_SRC_ZIP
IT_WITH_ALT_JAR_BINARY

IT_ENABLE_CACAO
IT_WITH_CACAO_HOME
IT_WITH_CACAO_SRC_ZIP
IT_WITH_CACAO_SRC_DIR

IT_ENABLE_JAMVM
IT_WITH_JAMVM_SRC_ZIP

IT_DISABLE_OPTIMIZATIONS
IT_ENABLE_WERROR
IT_ENABLE_JAR_COMPRESSION
IT_SET_SHARK_BUILD
IT_ENABLE_ZERO_BUILD
IT_CHECK_ADDITIONAL_VMS
IT_ENABLE_NON_NSS_CURVES
IT_ENABLE_HG
IT_WITH_HG_REVISION
IT_WITH_TZDATA_DIR
IT_GETDTDTYPE_CHECK
IT_JAVAH

IT_WITH_PAX
AC_CONFIG_FILES([pax-mark-vm], [chmod +x pax-mark-vm])

dnl pkgconfig cannot be used to find these headers and libraries.
AC_CHECK_HEADERS([cups/cups.h cups/ppd.h],[]
	,[AC_MSG_ERROR([Could not find CUPS headers;
	install CUPS (including cups-devel on binary distros).])])
AC_CHECK_HEADERS([X11/X.h],[]
	,[AC_MSG_ERROR([Could not find X.org headers;
	install X.org (including xorg-x11-proto-devel on binary distros).])])

IT_CHECK_FOR_ZLIB
IT_CHECK_FOR_JPEG
IT_CHECK_FOR_PNG
IT_CHECK_FOR_GIF
IT_CHECK_FOR_LCMS
IT_CHECK_FOR_KERBEROS
AC_CONFIG_FILES([remove-intree-libraries.sh])

dnl Check for Xproto headers and libraries.
PKG_CHECK_MODULES(XPROTO, xproto,[XPROTO_FOUND=yes],[XPROTO_FOUND=no])
if test "x${XPROTO_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find Xproto headers - \
  Try installing xorg-x11-proto-devel.])
fi
AC_SUBST(XT_CFLAGS)
AC_SUBST(XT_LIBS)

dnl Check for libXt headers and libraries.
PKG_CHECK_MODULES(XT, xt,[XT_FOUND=yes],[XT_FOUND=no])
if test "x${XT_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find Xt - \
  Try installing libXt-devel.])
fi
AC_SUBST(XT_CFLAGS)
AC_SUBST(XT_LIBS)

dnl Check for libX11 headers and libraries.
PKG_CHECK_MODULES(X11, x11,[X11_FOUND=yes],[X11_FOUND=no])
if test "x${X11_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find x11 - \
  Try installing libX11-devel.])
fi
AC_SUBST(X11_CFLAGS)
AC_SUBST(X11_LIBS)

dnl Check for libXinerama headers and libraries.
PKG_CHECK_MODULES(XINERAMA, xinerama,[XINERAMA_FOUND=yes]
	,[XINERAMA_FOUND=no])
if test "x${XINERAMA_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find Xinerama - \
  Try installing libXinerama-devel.])
fi
AC_SUBST(XINERAMA_CFLAGS)
AC_SUBST(XINERAMA_LIBS)

if test "x${ENABLE_XRENDER}" = "xyes"
then
  PKG_CHECK_MODULES(XRENDER, xrender, [XRENDER_FOUND=yes], [XRENDER_FOUND=no])
  if test "x${XRENDER_FOUND}" = xno
  then
    AC_MSG_ERROR([Could not find Xrender extension - \
Try installing libXrender-devel or configure --disable-xrender.])
  fi
fi

AC_MSG_CHECKING([for absolute java home install dir])
AC_ARG_WITH([abs-install-dir],
	    [AS_HELP_STRING([--with-abs-install-dir], 
			    [The absolute path where the j2sdk-image dir will be installed])],
	    [if test "x${withval}" = x; then
		ABS_JAVA_HOME_DIR="${abs_top_builddir}/${OPENJDK_BUILD_DIR}/j2sdk-image"
	     else
		ABS_JAVA_HOME_DIR="${withval}"
	     fi], [ABS_JAVA_HOME_DIR="${abs_top_builddir}/${OPENJDK_BUILD_DIR}/j2sdk-image"])
AC_MSG_RESULT([${ABS_JAVA_HOME_DIR}])
AC_SUBST(ABS_JAVA_HOME_DIR)

AC_CONFIG_FILES([jconsole.desktop])
AC_CONFIG_FILES([policytool.desktop])

if test "x${ENABLE_SYSTEMTAP}" = xyes; then
AC_CHECK_HEADER([sys/sdt.h], [SDT_H_FOUND='yes'],
                [SDT_H_FOUND='no';
                   AC_MSG_ERROR([systemtap support needs sys/sdt.h header])])

 AC_MSG_CHECKING([working sys/sdt.h and g++ support])
 AC_LANG_PUSH([C++])
 AC_LINK_IFELSE([AC_LANG_PROGRAM([[
#include <sys/sdt.h>
class ProbeClass
{
private:
  int& ref;
  const char *name;

public:
  ProbeClass(int& v, const char *n) : ref(v), name(n)
  {
    DTRACE_PROBE2(_test_, cons, name, ref);
  }

  void method(int min)
  {
    DTRACE_PROBE3(_test_, meth, name, ref, min);
    ref -= min;
  }

  ~ProbeClass()
  {
    DTRACE_PROBE2(_test_, dest, name, ref);
  }
};
]],
[[
  int i = 64;
  DTRACE_PROBE1(_test_, call, i);
  ProbeClass inst = ProbeClass(i, "call");
  inst.method(24);
]])], [AC_MSG_RESULT([yes])], [AC_MSG_ERROR([systemtap sdt.h or g++ too old])])
  AC_LANG_POP([C++])

  ABS_CLIENT_LIBJVM_SO="${ABS_JAVA_HOME_DIR}/jre/lib/${INSTALL_ARCH_DIR}/client/libjvm.so"
  ABS_SERVER_LIBJVM_SO="${ABS_JAVA_HOME_DIR}/jre/lib/${INSTALL_ARCH_DIR}/server/libjvm.so"
  AC_SUBST(ABS_CLIENT_LIBJVM_SO)
  AC_SUBST(ABS_SERVER_LIBJVM_SO)
  AC_CONFIG_FILES([tapset/hotspot.stp])
  AC_CONFIG_FILES([tapset/hotspot_jni.stp])
  AC_CONFIG_FILES([tapset/jstack.stp])
  AC_CONFIG_FILES([tapset/hotspot_gc.stp])
fi

dnl Check for libXtst headers and libraries.
PKG_CHECK_MODULES(XTST, xtst,[XTST_FOUND=yes],[XTST_FOUND=no])
if test "x${XTST_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find Xtst - \
  Try installing libXtst-devel.])
fi
AC_SUBST(XTST_CFLAGS)
AC_SUBST(XTST_LIBS)

dnl Check for freetype2 headers and libraries.
PKG_CHECK_MODULES(FREETYPE2, freetype2,[FREETYPE2_FOUND=yes]
	,[FREETYPE2_FOUND=no])
if test "x${FREETYPE2_FOUND}" = xno
then
  AC_MSG_ERROR([Could not find freetype2 - \
  Try installing freetype2-devel.])
fi
AC_SUBST(FREETYPE2_CFLAGS)
AC_SUBST(FREETYPE2_LIBS)

dnl Check for alsa headers and libraries (only required for Linux).
if test "x${BUILD_OS_DIR}" = "xlinux"
then
  PKG_CHECK_MODULES(ALSA, alsa,[ALSA_FOUND=yes],[ALSA_FOUND=no])
  if test "x${ALSA_FOUND}" = xno
  then
    AC_MSG_ERROR([Could not find alsa - \
    Try installing alsa-lib-devel.])
  fi
AC_SUBST(ALSA_CFLAGS)
AC_SUBST(ALSA_LIBS)
fi

if test "x${enable_pulse_java}" = "xyes"
then
  dnl Check for pulseaudio libraries.
  PKG_CHECK_MODULES(LIBPULSE,[libpulse >= 0.9.11],[LIBPULSE_FOUND=yes]
    ,[LIBPULSE_FOUND=no])
  if test "x${LIBPULSE_FOUND}" = xno
  then
    AC_MSG_ERROR([Could not find pulseaudio>=0.9.11 libraries - \
    Try installing pulseaudio-libs-devel>=0.9.11.])
  fi
  AC_SUBST(LIBPULSE_CFLAGS)
  AC_SUBST(LIBPULSE_LIBS)
fi

if test "x${ZERO_BUILD_TRUE}" = x || test "x${ADD_ZERO_BUILD_TRUE}" = x; then
  dnl Check for libffi headers and libraries.
  PKG_CHECK_MODULES(LIBFFI, libffi,[LIBFFI_FOUND=yes],[LIBFFI_FOUND=no])
  if test "x${LIBFFI_FOUND}" = xno
  then
    LIBFFI_FOUND=
    AC_CHECK_HEADER([ffi.h],[LIBFFI_CFLAGS=],[LIBFFI_FOUND=no])
    AC_SEARCH_LIBS([ffi_call], [ffi],[LIBFFI_LIBS=-lffi],[LIBFFI_FOUND=no])
    if test "x${LIBFFI_FOUND}" = xno
    then
      AC_MSG_ERROR([Could not find libffi headers - \
      Try installing libffi-devel, libffi-dev or libffi4-dev.])
    fi
  fi
fi
AC_SUBST(LIBFFI_CFLAGS)
AC_SUBST(LIBFFI_LIBS)

AC_ARG_WITH([llvm-config],
            [AS_HELP_STRING([--with-llvm-config],
                            [llvm-config binary (default is llvm-config)])],
            [
              if test "x${withval}" != x && test "x${withval}" != xyes && test "x${withval}" != xno; then
	        if which ${withval} >/dev/null 2>&1 && ${withval} --version >/dev/null 2>&1; then
		  LLVM_CONFIG=${withval}
		else
		  AC_MSG_ERROR([invalid argument for --with-llvm-config ($withval)])
		fi
              fi
            ])

if test "x${SHARK_BUILD_TRUE}" = x || test "x${ADD_SHARK_BUILD_TRUE}" = x; then
  if test "x${LLVM_CONFIG}" = x; then
    IT_FIND_TOOL([LLVM_CONFIG], [llvm-config])
  fi
  llvm_components="jit engine nativecodegen"
  LLVM_VERSION="`$LLVM_CONFIG --version`"
  dnl LLVM_CFLAGS
  LLVM_CFLAGS=
  for flag in $($LLVM_CONFIG --cxxflags $llvm_components); do
    if echo "$flag" | grep -q '^-[[ID]]'; then
      if test "$flag" != "-D_DEBUG"; then
        if test "x$LLVM_CFLAGS" != "x"; then
          LLVM_CFLAGS="$LLVM_CFLAGS "
        fi
        LLVM_CFLAGS="$LLVM_CFLAGS$flag"
      fi
    fi
  done
  LLVM_CFLAGS="$LLVM_CFLAGS -DSHARK_LLVM_VERSION=`echo $LLVM_VERSION | sed 's/\.//;s/svn.*//'`"
  dnl LLVM_LDFLAGS
  LLVM_LDFLAGS=
  for flag in $($LLVM_CONFIG --ldflags $llvm_components); do
    if echo "$flag" | grep -q '^-L'; then
      if test "x$LLVM_LDFLAGS" != "x"; then
        LLVM_LDFLAGS="$LLVM_LDFLAGS "
      fi
      LLVM_LDFLAGS="$LLVM_LDFLAGS$flag"
    fi
  done
  dnl LLVM_LIBS
  LIBS_SAVED=$LIBS
  LDFLAGS_SAVED=$LDFLAGS
  LDFLAGS=$LLVM_LDFLAGS
  AC_CHECK_LIB(LLVM-$LLVM_VERSION, LLVMGetNextInstruction,LLVM_SHARED_LIB=" -lLLVM-$LLVM_VERSION")
  LIBS=$LIBS_SAVED
  LDFLAGS=$LDFLAGS_SAVED
  LLVM_LIBS=
  for flag in $($LLVM_CONFIG --libs $llvm_components); do
    if test "x$LLVM_SHARED_LIB" != x && echo "$flag" | grep -q '^-lLLVM'; then
      continue
    fi
    if echo "$flag" | grep -q '^-l'; then
      if test "x$LLVM_LIBS" != "x"; then
        LLVM_LIBS="$LLVM_LIBS "
      fi
      LLVM_LIBS="$LLVM_LIBS$flag"
    fi
  done
  LLVM_LIBS="$LLVM_LIBS$LLVM_SHARED_LIB"
fi
AC_SUBST(LLVM_CFLAGS)
AC_SUBST(LLVM_LDFLAGS)
AC_SUBST(LLVM_LIBS)

# Arguments passed to configure.
AC_SUBST(CONFIGURE_ARGS)
CONFIGURE_ARGS="$ac_configure_args"

# Sanity check; make sure we have a JVM
if test "x$has_native_hotspot_port" = "xno" ; then
  if test "x${use_zero}" = "xno" ; then
    if test "x${ENABLE_CACAO}" = "xno" -a "x${ENABLE_JAMVM}" = "xno"; then
      AC_MSG_ERROR([No native HotSpot port available for ${host_cpu} and all alternatives disabled.])
    fi
  fi
fi

AC_OUTPUT
