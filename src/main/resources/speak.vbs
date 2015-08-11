Set oArgs = WScript.Arguments
    For Each s In oArgs
        CreateObject("sapi.spvoice").speak s
    Next
