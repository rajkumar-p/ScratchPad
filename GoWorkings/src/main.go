package main

import (
	"errors"
	"fmt"
	"github.com/jawher/mow.cli"
	"os"
	"strconv"
)

type BoolOpt bool

func (b *BoolOpt) Set(v string) error {
	parsed, err := strconv.ParseBool(v)
	if err != nil {
		return errors.New(fmt.Sprintf("Invalid boolean value %s", v))
	}

	*b = BoolOpt(parsed)
	return nil
}

func (b *BoolOpt) String() string {
	boolValue := bool(*b)
	return strconv.FormatBool(boolValue)
}

//func (b *BoolOpt) IsBoolFlag() bool {
//	return true
//}

func main()  {
	app := cli.App("cp", "Copy files around")
	app.Spec = "[-r] SRC ... DST"

	var (
		//recursive = app.BoolOpt("r recursive", false, "Copy files recursively")
		src = app.StringsArg("SRC", nil, "Source files to copy")
		dst = app.StringArg("DST", "", "Destination where to copy files to")
	)

	recursiveValue := BoolOpt(false)
    app.VarOpt("r recursive", &recursiveValue, "Copy files recursively")

	app.Action = func() {
		fmt.Printf("Copying %v to %s [recursively: %v]\n", *src, *dst, recursiveValue)
	}

	app.Run(os.Args)
}
