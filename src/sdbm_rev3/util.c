#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#ifdef SDBM
#include "sdbm.h"
#else
#include "ndbm.h"
#endif

const char * const sys_errlist[];
int sys_nerr;

void
oops(s1, s2)
register char *s1;
register char *s2;
{
	//extern int errno, sys_nerr;
	//extern char *sys_errlist[];
	extern char *progname;

	if (progname)
		fprintf(stderr, "%s: ", progname);
	fprintf(stderr, s1, s2);
	if (errno > 0 && errno < sys_nerr)
		fprintf(stderr, " (%s)", sys_errlist[errno]);
	fprintf(stderr, "\n");
	exit(1);
}

int
okpage(pag)
char *pag;
{
	register unsigned n;
	register off;
	register short *ino = (short *) pag;

	if ((n = ino[0]) > PBLKSIZ / sizeof(short))
		return 0;

	if (!n)
		return 1;

	off = PBLKSIZ;
	for (ino++; n; ino += 2) {
		if (ino[0] > off || ino[1] > off ||
		    ino[1] > ino[0])
			return 0;
		off = ino[1];
		n -= 2;
	}

	return 1;
}
