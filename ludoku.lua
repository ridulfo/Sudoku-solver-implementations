local function is_allowed(board, row, col, val)
	for i = 1, 9 do
		if board[row][i] == val then
			return false
		end

		if board[i][col] == val then
			return false
		end
	end

	local row_start = 1 + 3 * math.floor((row - 1) / 3)
	local col_start = 1 + 3 * math.floor((col - 1) / 3)

	for i = row_start, row_start + 2 do
		for j = col_start, col_start + 2 do
			if board[i][j] == val then
				return false
			end
		end
	end

	return true
end

local function solve(board)
	local solved = false
	local row = 1
	local col = 1

	while not solved do
		local next = false
		for n = board[row][col] + 1, 9 do
			if is_allowed(board, row, col, n) then
				board[row][col] = n
				next = true
				break
			end
		end

		if next then
			if row == 9 and col == 9 then
				solved = true
			end
			col = col + 1
			if col > 9 then
				row = row + 1
				col = 1
			end
		else
			board[row][col] = 0
			col = col - 1
			if col < 1 then
				row = row - 1
				col = 9
			end
		end
	end
	return board
end

function inspect(board)
	for i = 1, 9 do
		for j = 1, 9 do
			io.write(board[i][j] .. " ")
			if j % 3 == 0 then
				io.write("  ")
			end
		end
		io.write("\n")
		if i % 3 == 0 then
			io.write("\n")
		end
	end
end

local function create_grid()
	local grid = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }
	return grid
end


local grid = create_grid()
solve(grid)
local grid = create_grid()
local startTime = os.clock()
solve(grid)
local endTime = os.clock()
local elapsedTime = (endTime - startTime) * 10 ^ 6
print(elapsedTime .. " us")

