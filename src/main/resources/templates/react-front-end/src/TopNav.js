function TopNav(){
    return(
    <>
     <nav className="navbar navbar-expand-lg navbar-light bg-light ">
     <div className="container-fluid">
        <a className="navbar-brand" href="">
        <img src="./logo.png" alt="" width="40" height="40" class="d-inline-block align-text-top"/>
            &nbsp;&nbsp;
        Javaguidings.com</a>
        <form class="d-flex">
      <button class="btn btn-outline-success" type="submit">Login</button>
    </form>
     </div>

     </nav>
{/* Algorithm to display dynamic categories related to the current topic */}
   <ul className="nav justify-content-center">
    <ol className="nav-item"><a className="nav-link" href="">Java Exception Handling</a> </ol>
    <ol className="nav-item"><a className="nav-link" href="">Java Files</a> </ol>
    <ol className="nav-item"><a className="nav-link" href="">Java Concurrency</a> </ol>

   </ul>

       
    </>
    
    );
    }
    export default TopNav;