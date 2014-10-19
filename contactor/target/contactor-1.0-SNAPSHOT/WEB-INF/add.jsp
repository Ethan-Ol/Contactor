<html>
<body>
	<h1>Add Customer</h1>
 <form method="post" action="add">
  <div class="row">
    <div class="large-12 columns">
      <div class="row collapse">
      <div class="small-3 columns">
          <span class="prefix">Label</span>
        </div>
        <div class="small-10 columns">
          <input type="text" placeholder="Hex Value">
        </div>
        <div class="small-2 columns">
          <a href="#" class="button postfix">Go</a>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="large-6 columns">
      <div class="row collapse prefix-radius">
        <div class="small-3 columns">
          <span class="prefix">Label</span>
        </div>
        <div class="small-9 columns">
          <input type="text" placeholder="Value">
        </div>
      </div>
    </div>
    <div class="large-6 columns">
      <div class="row collapse postfix-radius">
        <div class="small-9 columns">
          <input type="text" placeholder="Value">
        </div>
        <div class="small-3 columns">
          <span class="postfix">Label</span>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="large-6 columns">
      <div class="row collapse prefix-round">
        <div class="small-3 columns">
          <a href="#" class="button prefix">Go</a>
        </div>
        <div class="small-9 columns">
          <input type="text" placeholder="Value">
        </div>
      </div>
    </div>
    <div class="large-6 columns">
      <div class="row collapse postfix-round">
        <div class="small-9 columns">
          <input type="text" placeholder="Value">
        </div>
        <div class="small-3 columns">
          <a href="#" class="button postfix">Go</a>
        </div>
      </div>
    </div>
  </div>
</form>
	<form method="post" action="add" >
		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="name" id="name" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                            maxlength="30" name="email" id="email" />
				</td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>
 
</body>
</html>