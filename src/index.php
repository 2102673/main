<?php

function validateInput($input)
{
    //IF INPUT IS <SCRIPT> OR <SCRIPT/> OR </SCRIPT> OR </SCRIPT/> RETURN INVALID INPUT
    if (preg_match("/<script>/i", $input) || preg_match("/<script\/>/i", $input) || preg_match("/<\/script>/i", $input) || preg_match("/<\/script\/>/i", $input)) {
        $input = "Invalid Input";
    }

    // PREVENT SQL INJECTION ATTACKS
    if (!preg_match('/^[a-zA-Z0-9]+$/', $input)) {
        $input = "Invalid Input";
    }
    // ELSE RETURN INPUT
    return $input;
}

if (isset($_POST['submit'])) {
    // Use trim to remove leading and trailing whitespaces
    $searchTerm = trim($_POST['search']);
    
    // Validate the input
    $searchTerm = validateInput($searchTerm);

    // If the input is invalid, CLEAR the search term and stay on the home page
    if ($searchTerm == "Invalid Input") {
        $searchTerm = "";
    }
    // If the input is valid, go to a new page to display the search term
    else {
        header("Location: results.php?searchTerm=" . urlencode($searchTerm));
        exit();
    }
   
}
?>
<!DOCTYPE html>
<html>

<head>
    <title>Home Page</title>
</head>

<body>

    <div>
        <h1>PHP Website</h1>
        <form action="<?php echo $_SERVER['PHP_SELF'] ?>" method="post">
            <div>
                <label>Search</label>
                <input type="text" name="search" required placeholder="Enter Your Search Term">
            </div>
            <div>
                <button type="submit" name="submit">Submit</button>
            </div>

        </form>
    </div>
</body>

</html>
