package com.xyzcorp

object MyExtensions:
    extension (s: String)
        def exclaim: String = s + "!"
        def waveCase: String =
            s.zipWithIndex
                .map((c, i) =>
                    if c.isLetter then
                        if i % 2 == 0 then c.toUpper
                        else c.toLower
                    else c
                    end if
                )
                .mkString
