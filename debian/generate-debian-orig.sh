
tarball=openjdk-6-src-b38-20_jan_2016.tar.xz
version=6b38-1.13.10
hotspot=hotspot-hs20.tar.gz
cacaotb=cacao-68fe50ac34ec.tar.gz
jamvmtb=jamvm-2.0.0.tar.gz
base=openjdk-6
pkgdir=$base-$version
origtar=${base}_${version}.orig.tar.gz
tarballdir=6b38

icedtea_checkout=icedtea6-1.13.10
debian_checkout=openjdk6

if [ -d $pkgdir ]; then
    echo directory $pkgdir already exists
    exit 1
fi

if [ -d $pkgdir.orig ]; then
    echo directory $pkgdir.orig already exists
    exit 1
fi

if ! which automake-1.11 >/dev/null 2>&1; then
    echo should use automake1.11 to generate the autotools files
    exit 1
fi

if [ -f $origtar ]; then
    tar xf $origtar
    if [ -d $pkgdir.orig ]; then
	mv $pkgdir.orig $pkgdir
    fi
    tar -c -f - -C $icedtea_checkout . | tar -x -f - -C $pkgdir
    cp -a $debian_checkout $pkgdir/debian
else
    rm -rf $pkgdir.orig
    mkdir -p $pkgdir.orig
    case "$base" in
      openjdk*)
	#cp -p $hotspot $pkgdir.orig/
	cp -p $tarballdir/$tarball $pkgdir.orig/
	#cp -a $tarballdir/drops $pkgdir.orig/
	#if [ $(lsb_release -is) = Ubuntu ]; then
	    cp -p $tarballdir/$cacaotb $pkgdir.orig/
	#fi
	cp -p $tarballdir/$jamvmtb $pkgdir.orig/
	;;
    esac
    tar -c -f - -C $icedtea_checkout . | tar -x -f - -C $pkgdir.orig
    (
	cd $pkgdir.orig
	sh autogen.sh
	rm -rf autom4te.cache
    )
    cp -a $pkgdir.orig $pkgdir
    rm -rf $pkgdir.orig/.hg
fi

echo "Build debian diff in $pkgdir/"
cp -a $debian_checkout $pkgdir/debian
(
  cd $pkgdir
  ls
  patch -p1 < debian/icedtea-patch.diff
  sh autogen.sh
  rm -rf autom4te.cache
)
