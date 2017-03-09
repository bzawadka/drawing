[![Build Status](https://travis-ci.org/bzawadka/drawing.png?branch=master)](https://travis-ci.org/bzawadka/drawing)
[![codecov.io](https://codecov.io/github/bzawadka/drawing/coverage.svg?branch=master)](https://codecov.io/github/bzawadka/drawing?branch=master)

# drawing
simple console drawing program

## main class
pl.bzawadka.drawing.App

## commands
`C w h` - create canvas of width w and height h

`L x1 y1 x2 y2` - draw a line

`R x1 y1 x2 y2` - draw a rectangle

`B x1 y1 c` - bucket fill from given point with character c

`Q` - quit the program

commands are case insensitive

## exception handling
when incorrect command given, program will terminate

## drawing outside of canvas
whatever is outside of canvas will be ignored
